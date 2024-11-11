package be.neoo.repository;


import be.neoo.entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
