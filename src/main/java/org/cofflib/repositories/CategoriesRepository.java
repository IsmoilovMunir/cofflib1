package org.cofflib.repositories;

import org.cofflib.dto.CategoriesDto;
import org.cofflib.entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Integer> {
    Optional<Object> findById(List<CategoriesDto> categoriesId);
}
