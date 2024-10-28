package be.neoo.repository;

import be.neoo.entities.Product;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    public List<Product> getProducts (EntityManager em){
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }

    public Product save(EntityManager em, Product product){
        em.persist(product);
        return em.find(Product.class, product.getId());
    }

    /**
     * update product
     * @param em
     * @param id
     * @param updateProduct
     * @return
     */
    public Product update(EntityManager em,int id , Product updateProduct){
        Product existingProduct = em.find(Product.class, id);
        if (existingProduct != null){
            existingProduct.setName(updateProduct.getName());
            existingProduct.setActive(updateProduct.getActive());
            existingProduct.setMinimumQuantity(updateProduct.getMinimumQuantity());
            existingProduct.setQuantityBox(updateProduct.getQuantityBox());
            existingProduct.setQuantityPackage(updateProduct.getQuantityPackage());
            existingProduct.setCode(updateProduct.getCode());
            existingProduct.setCategory(updateProduct.getCategory());
            existingProduct.setBrand(updateProduct.getBrand());
            existingProduct.setUnitCostPrice(updateProduct.getUnitCostPrice());
            em.merge(existingProduct);
        }
        return existingProduct;
    }
}
