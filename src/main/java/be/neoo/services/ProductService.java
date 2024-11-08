package be.neoo.services;

import be.neoo.connection.EMF;
import be.neoo.controller.ProductController;
import be.neoo.dto.BrandDto;
import be.neoo.dto.CategoryDto;
import be.neoo.dto.ProductDto;
import be.neoo.entities.Brand;
import be.neoo.entities.Category;
import be.neoo.entities.Product;
import be.neoo.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    ProductRepository productRepository;
    private ModelMapper modelMapper;

    /**
     * Constructor
     *
     * @param productRepository
     * @param modelMapper
     */
    public ProductService(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Get all product
     *
     * @return
     */
    public List<ProductDto> getProducts() {
        EntityManager em = EMF.getEM();
        List<ProductDto> productDtos = new ArrayList<>();
        try {
            List<Product> products = productRepository.getProducts(em);
            products.forEach(product -> {

                BrandDto brandDto = modelMapper.map(product.getBrand(), BrandDto.class);
                CategoryDto categoryDto = modelMapper.map(product.getCategory(), CategoryDto.class);
                ProductDto productDto = modelMapper.map(product, ProductDto.class);

                productDto.setBrandDto(brandDto);
                productDto.setCategoryDto(categoryDto);

                productDtos.add(productDto);
            });

        } finally {
            em.close();
        }
        return productDtos;
    }


    /**
     * Get all product that has a stock greater than zero
     * @return
     */
    public List<ProductDto> getProductsStockQuantityGtZero() {
        EntityManager em = EMF.getEM();
        List<ProductDto> productDtos = new ArrayList<>();
        try {
            List<Product> products = productRepository.getProductsStockQuantityGtZero(em);
            products.forEach(product -> {

                BrandDto brandDto = modelMapper.map(product.getBrand(), BrandDto.class);
                CategoryDto categoryDto = modelMapper.map(product.getCategory(), CategoryDto.class);
                ProductDto productDto = modelMapper.map(product, ProductDto.class);

                productDto.setBrandDto(brandDto);
                productDto.setCategoryDto(categoryDto);

                productDtos.add(productDto);
            });

        } finally {
            em.close();
        }
        return productDtos;
    }

    /**
     * add new product
     *
     * @param productDto
     * @return
     */
    public ProductDto save(ProductDto productDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        Brand brand = modelMapper.map(productDto.getBrandDto(), Brand.class);
        Category category = modelMapper.map(productDto.getCategoryDto(), Category.class);

        Product product = modelMapper.map(productDto, Product.class);
        product.setBrand(brand);
        product.setCategory(category);


        try {
            trans.begin();
            log.info("Saving brand id {}", product.getBrand().getId());
            log.info("Saving brand name {}", product.getBrand().getName());
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
     *
     * @param id
     * @param productDto
     * @return
     */
    public ProductDto update(int id, ProductDto productDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        Product updateProduct = modelMapper.map(productDto, Product.class);
        ProductDto productDtoUpdated = new ProductDto();
        try {
            trans.begin();
            updateProduct = productRepository.update(em, id, updateProduct);
            trans.commit();

            BrandDto brandDto = modelMapper.map(updateProduct.getBrand(), BrandDto.class);
            CategoryDto categoryDto = modelMapper.map(updateProduct.getCategory(), CategoryDto.class);
            productDtoUpdated = modelMapper.map(updateProduct, ProductDto.class);
            productDtoUpdated = modelMapper.map(productDtoUpdated, ProductDto.class);
            productDtoUpdated.setBrandDto(brandDto);
            productDtoUpdated.setCategoryDto(categoryDto);
        } catch (Exception e) {
            trans.rollback();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }

        return productDtoUpdated;
    }

    /**
     * check if code exist
     *
     * @param code
     * @return
     */
    public boolean codeExist(String code) {
        EntityManager em = EMF.getEM();
        return productRepository.codeExist(em, code);
    }
}
