package be.neoo.services;


import be.neoo.connection.EMF;
import be.neoo.dto.BrandDto;
import be.neoo.dto.OrderDto;
import be.neoo.dto.OrderProductDto;
import be.neoo.dto.ProductDto;
import be.neoo.entities.*;
import be.neoo.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<OrderDto> getOrders() {
        EntityManager em = EMF.getEM();
        List<OrderDto> orderDtos = new ArrayList<>();
        List<Order> orders = orderRepository.getOrders(em);
        orders.forEach(order -> orderDtos.add(modelMapper.map(order, OrderDto.class)));
        return orderDtos;
    }

    public List<OrderProductDto> getOProductsByOrderId( int id) {
        EntityManager em = EMF.getEM();
        List<OrderProductDto> orderProductDtos = new ArrayList<>();
        List<OrderProduct> orderProducts = orderRepository.getOProductsByOrderId(em, id);
        orderProducts.forEach(product -> {
            log.info("id produit " + product.getProduct().getId());
            ProductDto productDto = modelMapper.map(product.getProduct(), ProductDto.class);
            OrderProductDto orderProductDto = modelMapper.map(product, OrderProductDto.class);
            orderProductDto.setProductDto(productDto);
            orderProductDtos.add(orderProductDto);
        });
        return orderProductDtos;
    }
}
