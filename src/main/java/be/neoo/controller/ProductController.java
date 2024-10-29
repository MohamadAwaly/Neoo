package be.neoo.controller;


import be.neoo.dto.ProductDto;
import be.neoo.entities.Product;
import be.neoo.services.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/products")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);


    ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * get All product reference.
     * @return
     */
    @GetMapping("/getAllProducts")
    public List<ProductDto> getProducts() {
        return this.productService.getProducts();
    }

    /**
     * Save new Product reference.
     * @param productDto
     * @return
     */
    @PostMapping("/save")
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping("/update/{id}")
    public ProductDto update(@PathVariable int id, @RequestBody ProductDto productDto) {
        return productService.update(id , productDto);
    }


}
