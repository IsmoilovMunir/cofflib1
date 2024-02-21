package org.cofflib.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.CategoriesDto;
import org.cofflib.dto.ExpensesDto;
import org.cofflib.entity.Categories;
import org.cofflib.entity.Expenses;
import org.cofflib.entity.PaymentsType;
import org.cofflib.entity.Users;
import org.cofflib.repositories.CategoriesRepository;
import org.cofflib.repositories.ExpensesRepository;
import org.cofflib.repositories.PaymentRepository;
import org.cofflib.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpensesCRUDService implements CRUDService<ExpensesDto> {
    private final ExpensesRepository expensesRepository;
    private final CategoriesRepository categoriesRepository;
    private final PaymentRepository paymentRepository;
    private final UsersRepository usersRepository;


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
        Expenses expenses = mapToEntity(expensesDto);

        Integer categoriesId = expensesDto.getCategoriesId();
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow();

        Integer paymentsId = expensesDto.getPaymentId();
        PaymentsType paymentsType = paymentRepository.findById(paymentsId).orElseThrow();

        Integer userId = expensesDto.getUserId();
        Users users = usersRepository.findById(userId).orElseThrow();

        expenses.setUsers(users);
        expenses.setPaymentsType(paymentsType);
        expenses.setCategories(categories);
        expensesRepository.save(expenses);

    }

    @Override
    public void update(ExpensesDto expensesDto) {
        log.info("Update ");
        Expenses expenses = mapToEntity(expensesDto);
        Integer categoriesId = expensesDto.getCategoriesId();
        Categories categories = categoriesRepository.findById(categoriesId).orElseThrow();

        Integer paymentsId = expensesDto.getPaymentId();
        PaymentsType paymentsType = paymentRepository.findById(paymentsId).orElseThrow();
        expenses.setPaymentsType(paymentsType);
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
        expensesDto.setCategoriesId(CategoriesCRUDService.mapToDto(expenses.getCategories()).getId());
        expensesDto.setCost(expenses.getCost());
        expensesDto.setPaymentId(PaymentCRUDService.mapToDto(expenses.getPaymentsType()).getId());
        expensesDto.setUserId(UsersCRUDService.mapToDto(expenses.getUsers()).getId());
        return expensesDto;

    }

    public static Expenses mapToEntity(ExpensesDto expensesDto) {
        Expenses expenses = new Expenses();
        expenses.setCost(expensesDto.getCost());

        expenses.setName(expensesDto.getName());
        expenses.setId(expensesDto.getId());
        return expenses;
    }
}
