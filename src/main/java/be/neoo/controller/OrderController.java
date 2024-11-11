package be.neoo.controller;


import be.neoo.dto.BrandDto;
import be.neoo.dto.OrderDto;
import be.neoo.dto.OrderProductDto;
import be.neoo.dto.ProductDto;
import be.neoo.entities.OrderProduct;
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
     *
     * @return
     */
    @GetMapping("/getorders")
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    /**
     * Get products by id order
     *
     * @return
     */
    @GetMapping("/getProductByIdOrder/{id}")
    public List<OrderProductDto> getOProductsByOrderId(@PathVariable int id) {
//        log.info("getProductsByOrderId {}", id);
//        List<ProductDto> productDtos =  orderService.getOProductsByOrderId(id);
//        productDtos.forEach(productDto -> {
//           log.info("productDto {}", productDto.getId());
//        });
        return orderService.getOProductsByOrderId(id);
    }


}
