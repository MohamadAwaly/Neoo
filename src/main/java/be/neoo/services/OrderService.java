package be.neoo.services;


import be.neoo.connection.EMF;
import be.neoo.dto.OrderDto;
import be.neoo.dto.OrderProductDto;
import be.neoo.entities.Customer;
import be.neoo.entities.Order;
import be.neoo.entities.OrderProduct;
import be.neoo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private OrderRepository orderRepository;
    ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    public OrderDto save(OrderDto orderDto) {
        EntityManager em = EMF.getEM();
        EntityTransaction trans = em.getTransaction();

        Order order = new Order();
        order.setDateOrder(orderDto.getDateOrder());
        order.setPayed(orderDto.getPayed());
        order.setPayementDate(orderDto.getPayementDate());
        order.setModeOfPayement(orderDto.getModeOfPayement());
        order.setDeliver(orderDto.getDeliver());
        order.setDeliverDate(orderDto.getDeliverDate());
        order.setOrderProducts(new ArrayList<>());

        orderDto.getOrderProducts().forEach(prod -> {
            OrderProduct orderProduct = modelMapper.map(prod, OrderProduct.class);
            orderProduct.setOrder(order); // Associez l'ordre à chaque produit
            order.getOrderProducts().add(orderProduct);
        });
        int idCustomer = orderDto.getCustomer().getId();
        try {
            trans.begin();
            orderRepository.save(em , order, idCustomer);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            if (trans.isActive()) {
                trans.rollback();
            }
            em.close();
        }
        return null;
    }
}
