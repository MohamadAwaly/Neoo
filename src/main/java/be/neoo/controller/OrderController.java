package be.neoo.controller;


import be.neoo.dto.OrderDto;
import be.neoo.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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

        orderDto.getOrderProducts().forEach(prod -> {
            log.info("Saving product {}" + prod.getId());
            log.info("Saving product {}" + prod.getProductDto().getId());
            log.info("Saving product {}" + prod.getProductDto().getName());
            log.info("Saving product {}");
            log.info("Saving product {}");
        });


        orderService.save(orderDto);
        return orderDto;
    }
}
