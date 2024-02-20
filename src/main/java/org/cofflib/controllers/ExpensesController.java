package org.cofflib.controllers;


import org.cofflib.dto.ExpensesDto;
import org.cofflib.services.ExpensesCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/expenses")
public class ExpensesController {
    private final ExpensesCRUDService expensesService;

    public ExpensesController(ExpensesCRUDService expensesService) {
        this.expensesService = expensesService;
    }

    @GetMapping("/{id}")
    public ExpensesDto getExpensesById(@PathVariable Integer id) {
        return expensesService.getById(id);
    }

    @GetMapping
    public Collection<ExpensesDto> getAllExpenses() {
        return expensesService.getAll();
    }


    @PostMapping
    public void createExpenses(@RequestBody ExpensesDto expensesDto) {
        expensesService.create(expensesDto);
    }

    @PutMapping("/{id}")
    public void updateExpenses(@PathVariable Integer id, @RequestBody ExpensesDto expensesDto) {
        expensesDto.setId(id);
        expensesService.update(expensesDto);

    }

    @DeleteMapping("/{id}")
    public void deleteExpenses(@PathVariable Integer id) {
        expensesService.delete(id);
    }

}
