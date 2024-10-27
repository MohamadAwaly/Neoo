package be.neoo.controller;


import be.neoo.dto.CategoryDto;
import be.neoo.services.CategoyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/categories")
public class CategoryController {
    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private CategoyService _categoyService;

    public CategoryController(CategoyService categoyService) {
        this._categoyService = categoyService;
    }

    @PostMapping("/save")
    public CategoryDto save(@RequestBody CategoryDto categoryDto) {
        return _categoyService.save(categoryDto);
    }

    @GetMapping("/getCategories")
    public List<CategoryDto> getAllCategories() {
        return _categoyService.getCategories();
    }

    @PutMapping("update/{id}")
    public CategoryDto update(@PathVariable("id") int id, @RequestBody CategoryDto categoryDto) {
        return _categoyService.update(id, categoryDto);
    }
}
