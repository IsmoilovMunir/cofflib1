package org.cofflib.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.ExpensesDto;
import org.cofflib.entity.Categories;
import org.cofflib.entity.Expenses;
import org.cofflib.repositories.CategoriesRepository;
import org.cofflib.repositories.ExpensesRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpensesCRUDService implements CRUDService<ExpensesDto> {
    private final ExpensesRepository expensesRepository;
    private final CategoriesRepository categoriesRepository;


    @Override
    public ExpensesDto getById(Integer id) {
        log.info("Get by ID " + id);
        Expenses expenses = expensesRepository.findById(id).orElseThrow();
        return mapToDto(expenses);
    }

    @Override
    public Collection<ExpensesDto> getAll() {
        log.info("Get All");
        return expensesRepository.findAll()
                .stream()
                .map(ExpensesCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(ExpensesDto expensesDto) {
        log.info("Create");
        Expenses expenses  = mapToEntity(expensesDto);
        Integer categoriesId = expensesDto.getCategoryId();
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow();
        expenses.setCategories(categories);
        expensesRepository.save(expenses);

    }

    @Override
    public void update(ExpensesDto expensesDto) {
        log.info("Update ");
        Expenses expenses  = mapToEntity(expensesDto);
        Integer categoriesId = expensesDto.getCategoryId();
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow();
        expenses.setCategories(categories);
        expensesRepository.save(expenses);

    }

    @Override
    public void delete(Integer id) {
        log.info("Delete " + id);
       expensesRepository.deleteById(id);

    }

    public static ExpensesDto mapToDto(Expenses expenses) {
        ExpensesDto expensesDto = new ExpensesDto();
        expensesDto.setId(expenses.getId());
        expensesDto.setName(expenses.getName());
        expensesDto.setCategoryId(CategoriesCRUDService.mapToDto(expenses.getCategories()).getId());
        expensesDto.setCost(expenses.getCost());
        expensesDto.setPaymentId(expenses.getPaymentId());
        expensesDto.setUserId(expenses.getUserId());
        return expensesDto;

    }

    public static Expenses mapToEntity(ExpensesDto expensesDto) {
        Expenses expenses = new Expenses();
        expenses.setCost(expensesDto.getCost());

        expenses.setName(expensesDto.getName());
        expenses.setPaymentId(expensesDto.getPaymentId());
        expenses.setUserId(expensesDto.getUserId());
        expenses.setId(expensesDto.getId());
        return expenses;
    }
}