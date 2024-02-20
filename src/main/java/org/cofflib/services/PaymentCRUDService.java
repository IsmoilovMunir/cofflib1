package org.cofflib.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cofflib.dto.PaymentsTypeDto;
import org.cofflib.entity.PaymentsType;
import org.cofflib.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;


@AllArgsConstructor
@Service
@Slf4j
public class PaymentCRUDService implements CRUDService<PaymentsTypeDto> {
    private final PaymentRepository repository;

    @Override
    public PaymentsTypeDto getById(Integer id) {
        log.info("Get by Id " + id);
        PaymentsType paymentsType = repository.findById(id).orElseThrow();
        return mapToDto(paymentsType);
    }

    @Override
    public Collection<PaymentsTypeDto> getAll() {
        log.info("Get All");
        return repository.findAll()
                .stream()
                .map(PaymentCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(PaymentsTypeDto item) {
        log.info("Create");
        PaymentsType paymentsType = mapToEntity(item);
        repository.save(paymentsType);
    }

    @Override
    public void update(PaymentsTypeDto item) {
        log.info("Update");
        PaymentsType paymentsType = mapToEntity(item);
        repository.save(paymentsType);
    }

    @Override
    public void delete(Integer id) {
        System.out.println("delete");
        repository.deleteById(id);

    }

    public static PaymentsTypeDto mapToDto(PaymentsType paymentsType) {
        PaymentsTypeDto paymentsTypeDto = new PaymentsTypeDto();
        paymentsTypeDto.setId(paymentsType.getId());
        paymentsTypeDto.setName(paymentsType.getName());
        return paymentsTypeDto;
    }

    public static PaymentsType mapToEntity(PaymentsTypeDto paymentsTypeDto) {
        PaymentsType paymentsType = new PaymentsType();
        paymentsType.setId(paymentsTypeDto.getId());
        paymentsType.setName(paymentsTypeDto.getName());
        return paymentsType;
    }

}
