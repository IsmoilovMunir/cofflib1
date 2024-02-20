package org.cofflib.services;

import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.CategoriesDto;
import org.cofflib.entity.Categories;
import org.cofflib.repositories.CategoriesRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service

@Slf4j

public class CategoriesCRUDService implements CRUDService<CategoriesDto> {
    private final CategoriesRepository repository;

    public CategoriesCRUDService(CategoriesRepository repository) {
        this.repository = repository;
    }


    @Override
    public CategoriesDto getById(Integer id) {
        log.info("Get by ID " + id);
        return mapToDto(repository.findById(id).orElseThrow());
    }

    @Override
    public Collection<CategoriesDto> getAll() {
        log.info("Get all");


        return repository.findAll()
                .stream()
                .map(CategoriesCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(CategoriesDto item) {
        log.info("Create");
        Categories categories = mapToEntity(item);
        repository.save(categories);
    }


    @Override
    public void update(CategoriesDto item) {
        log.info("Update ");
        Categories categories = mapToEntity(item);
        repository.save(categories);


    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
        repository.deleteById(id);
    }

    public static CategoriesDto mapToDto(Categories categories) {
        CategoriesDto categoriesDto = new CategoriesDto();
        categoriesDto.setId(categories.getId());
        categoriesDto.setName(categories.getName());
        return categoriesDto;
    }

    public static Categories mapToEntity(CategoriesDto categoriesDto) {
        Categories categories = new Categories();
        categories.setId(categoriesDto.getId());
        categories.setName(categoriesDto.getName());
        return categories;
    }
}
