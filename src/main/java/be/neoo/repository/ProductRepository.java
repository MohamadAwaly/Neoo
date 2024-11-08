package be.neoo.repository;

import be.neoo.entities.Product;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    /**
     * Get all product
     * @param em
     * @return
     */
    public List<Product> getProducts(EntityManager em) {
        return em.createNamedQuery("product.findAll", Product.class).getResultList();
    }

    /**
     * Get all product that has a stock greater than zero
     * @param em
     * @return
     */
    public List<Product> getProductsStockQuantityGtZero(EntityManager em) {
        return em.createNamedQuery("product.stockQuantityGtZero", Product.class).getResultList();
    }

    /**
     * add new product reference
     * @param em
     * @param product
     * @return
     */
    public Product save(EntityManager em, Product product) {
        em.persist(product);
        return em.find(Product.class, product.getId());
    }

    /**
     * update product
     *
     * @param em
     * @param id
     * @param updateProduct
     * @return
     */
    public Product update(EntityManager em, int id, Product updateProduct) {
        Product existingProduct = em.find(Product.class, id);
        if (existingProduct != null) {
            existingProduct.setName(updateProduct.getName());
            existingProduct.setActive(updateProduct.getActive());
            existingProduct.setMinimumQuantity(updateProduct.getMinimumQuantity());
            existingProduct.setQuantityBox(updateProduct.getQuantityBox());
            existingProduct.setQuantityPackage(updateProduct.getQuantityPackage());
            existingProduct.setCode(updateProduct.getCode());
            existingProduct.setCategory(updateProduct.getCategory());
            existingProduct.setBrand(updateProduct.getBrand());
            existingProduct.setUnitCostPrice(updateProduct.getUnitCostPrice());
            existingProduct.setTotalQuantity(updateProduct.getTotalQuantity());
            em.merge(existingProduct);
        }
        return existingProduct;
    }

    /**
     * check if code existe
     * @param em
     * @param code
     * @return
     */
    public boolean codeExist(EntityManager em, String code) {
        List<Product> results = em.createNamedQuery("product.findByCode", Product.class)
                .setParameter("code", code)
                .getResultList();
        return !results.isEmpty();
    }
}
