package org.cofflib.controllers;

import org.cofflib.dto.CategoriesDto;

import org.cofflib.services.CategoriesCRUDService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@Controller
@RestController
@RequestMapping("/categories")
public class CategoriesController {
    private final CategoriesCRUDService categoriesService;

    public CategoriesController(CategoriesCRUDService categoriesService) {
        this.categoriesService = categoriesService;
    }


    @GetMapping("/{id}")
    public CategoriesDto getCategoriesById(@PathVariable Integer id) {
        return categoriesService.getById(id);

    }

    @GetMapping
    public Collection<CategoriesDto> getAllCategories() {
        return categoriesService.getAll();
    }

    @PostMapping
    public void createCategories(@RequestBody CategoriesDto categoriesDto) {
        categoriesService.create(categoriesDto);
    }

    @PutMapping("/{id}")
    public void updateCategories(@PathVariable Integer id, @RequestBody CategoriesDto categoriesDto) {
        categoriesDto.setId(id);
        categoriesService.update(categoriesDto);
    }


    @DeleteMapping("/{id}")
    public void deleteCategories(@PathVariable Integer id) {
        categoriesService.delete(id);
    }
}

