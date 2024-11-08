package be.neoo.controller;


import be.neoo.dto.BrandDto;
import be.neoo.dto.OrderDto;
import be.neoo.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/customerOrder")
public class OrderController {

    private OrderService orderService;

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Save new Product reference.
     *
     * @param orderDto
     * @return
     */
    @PostMapping("/save")
    public OrderDto save(@RequestBody OrderDto orderDto) {
        orderService.save(orderDto);
        return orderDto;
    }

    /**
     * Get all customer orders
     * @return
     */
    @GetMapping("/getorders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

}
