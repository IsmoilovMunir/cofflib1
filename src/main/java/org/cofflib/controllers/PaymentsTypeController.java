package org.cofflib.controllers;

import org.cofflib.dto.PaymentsTypeDto;
import org.cofflib.services.PaymentCRUDService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/paymentsType")
public class PaymentsTypeController {
    private PaymentCRUDService paymentService;

    public PaymentsTypeController(PaymentCRUDService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    public PaymentsTypeDto getPaymentTypeById(@PathVariable Integer id) {
        return paymentService.getById(id);
    }

    @GetMapping
    public Collection<PaymentsTypeDto> getAllPaymentType() {
        return paymentService.getAll();
    }

    @PostMapping
    public void createPaymentType(@RequestBody PaymentsTypeDto paymentsTypeDto) {
        paymentService.create(paymentsTypeDto);
    }

    @PutMapping("/{id}")
    public void updatePaymentType(@PathVariable Integer id, @RequestBody PaymentsTypeDto paymentsTypeDto) {
        paymentService.update( paymentsTypeDto);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentType(@PathVariable Integer id) {
        paymentService.delete(id);
    }
}
