package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.dto.ProductDto;
import be.neoo.entities.Product;
import be.neoo.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    ProductRepository productRepository;
    private ModelMapper modelMapper;

    /**
     * Product List
     *
     * @param productRepository
     * @param modelMapper
     */
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * list products
     * @return
     */
    public List<ProductDto> getProducts() {
        EntityManager em = EMF.getEM();
        List<ProductDto> productDtos = new ArrayList<>();

        try {
            List<Product> products = productRepository.getProducts(em);
            products.forEach(product -> productDtos.add(modelMapper.map(product, ProductDto.class)));

        } finally {
            em.close();
        }
        return productDtos;
    }

    /**
     * add new product
     * @param productDto
     * @return
     */
    public ProductDto save(ProductDto productDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();
        Product product = modelMapper.map(productDto, Product.class);

        try {
            trans.begin();
            product = productRepository.save(em, product);
            trans.commit();
        } catch (Exception e) {
            if (trans.isActive()) {
                trans.rollback(); // Only rollback if transaction is active
            }
            e.printStackTrace(); // Log or handle exception as needed
        } finally {
            em.close();
        }
        return modelMapper.map(product, ProductDto.class);
    }

    /**
     * update Product
     * @param id
     * @param productDto
     * @return
     */
    public ProductDto update (int id, ProductDto productDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        Product updateProduct = modelMapper.map(productDto, Product.class);

        try{
            trans.begin();
            updateProduct = productRepository.update(em, id,updateProduct );
            trans.commit();
        }catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }

        return modelMapper.map(updateProduct, ProductDto.class);
    }
}
