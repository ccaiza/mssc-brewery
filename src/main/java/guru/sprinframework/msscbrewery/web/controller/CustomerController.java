/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller;

import guru.sprinframework.msscbrewery.services.CustomerService;
import guru.sprinframework.msscbrewery.web.model.CustomerDto;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService CustomerService) {
        this.customerService = CustomerService;
    }

    /**
     * Otiene los datos de un cliente
     *
     * @param customerId
     * @return
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable(name = "customerId") UUID customerId) {
        return new ResponseEntity<>(this.customerService.getCustomerById(customerId), HttpStatus.OK);
    }

}
