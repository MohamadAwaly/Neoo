package be.neoo.controller;


import org.springframework.web.bind.annotation.*;
import be.neoo.services.BrandService;
import org.slf4j.LoggerFactory;
import be.neoo.dto.BrandDto;
import org.slf4j.Logger;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/brands")
public class BrandController {

    private static final Logger log = LoggerFactory.getLogger(BrandController.class);

    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/save")
    public BrandDto save(@RequestBody BrandDto brandDto) {
       return brandService.save(brandDto);
    }

    @GetMapping("/getBrands")
    public List<BrandDto> getAllBrands() {
        return brandService.getBrands();
    }

    @PutMapping("/update/{id}")
    public BrandDto update(@PathVariable int id, @RequestBody BrandDto brandDto) {
        return brandService.update(id,brandDto);
    }

}
