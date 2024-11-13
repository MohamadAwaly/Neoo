package be.neoo.repository;


import be.neoo.entities.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    private static final Logger log = LoggerFactory.getLogger(OrderRepository.class);


    /**
     * add new order
     *
     * @param em
     * @param order
     * @return
     */
    public Order save(EntityManager em, Order order, int id) {
        Customer customer = em.find(Customer.class, id);
        order.setCustomer(customer);
        em.merge(order);
        return order;
    }

    public List<Order> getOrders(EntityManager em) {
        Query query = em.createNamedQuery("order.findAll", Order.class);
        return query.getResultList();
    }

    public List<OrderProduct> getOProductsByOrderId(EntityManager em, int id) {
        Query query = em.createNamedQuery("product.findByOrderId", Product.class).setParameter("orderId", id);
        List<OrderProduct> orderProducts = query.getResultList();
        return query.getResultList();
    }

    public Order updateOrder(EntityManager em, Order updateOrder, int id) {

        Order existingOrder = em.find(Order.class, id);

        //Update order fields
        existingOrder.setDateOrder(updateOrder.getDateOrder());
        existingOrder.setPayed(updateOrder.getPayed());
        existingOrder.setPayementDate(updateOrder.getPayementDate());
        existingOrder.setDeliver(updateOrder.getDeliver());
        existingOrder.setDeliverDate(updateOrder.getDeliverDate());
        existingOrder.setModeOfPayement(updateOrder.getModeOfPayement());
        existingOrder.setCustomer(updateOrder.getCustomer());

        //Delete products
        List<OrderProduct> productsToRemove = new ArrayList<>();

        productsToRemove = existingOrder.getOrderProducts().stream()
                .filter(orderProduct -> updateOrder.getOrderProducts().stream()
                        .noneMatch(orderProduct2 -> orderProduct.getId() == orderProduct2.getId()))
                .collect(Collectors.toList());
        productsToRemove.forEach(existingOrder::removeOrderProduct);

        // Ajouter ou mettre à jour les produits
        for (OrderProduct updatedProduct : updateOrder.getOrderProducts()) {
            Optional<OrderProduct> existingProduct = existingOrder.getOrderProducts().stream()
                    .filter(p -> p.getId() == updatedProduct.getId())
                    .findFirst();

            if (existingProduct.isPresent()) {
                // Mettre à jour le produit existant
                OrderProduct productToUpdate = existingProduct.get();
                productToUpdate.setQte(updatedProduct.getQte());
                productToUpdate.setUnitePrice(updatedProduct.getUnitePrice());
                productToUpdate.setDiscount(updatedProduct.getDiscount());
                // Autres mises à jour de champs de produit
            } else {
                // Ajouter un nouveau produit
                existingOrder.addOrderProduct(updatedProduct);
            }
        }
        // Sauvegarder les modifications
        em.merge(existingOrder);


        return existingOrder;
    }
}
