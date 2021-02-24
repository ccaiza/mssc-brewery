/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller;

import guru.sprinframework.msscbrewery.services.CustomerService;
import guru.sprinframework.msscbrewery.web.model.CustomerDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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

    /**
     * Permite crear una cerveza
     *
     * @param customerDto
     * @return
     */
    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody CustomerDto customerDto) {
        CustomerDto saveDto = this.customerService.saveNewCustomer(customerDto);
        HttpHeaders headers = new HttpHeaders();
        // TODO hay que agregar el url completo
        headers.add("Location", "/api/v1/customer" + saveDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * Actualiza los datos
     *
     * @param customerId
     * @param customerDto
     * @return
     */
    @PutMapping("/{customerId}")
    public ResponseEntity handleUpdate(@PathVariable(name = "customerId") UUID customerId,
            @Valid @RequestBody CustomerDto customerDto) {
        this.customerService.updateCustomer(customerId, customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina un dato de cliente. El metodo retorna el status vacio
     *
     * @param customerId
     */
    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "customerId") UUID customerId) {
        this.customerService.deleteById(customerId);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List> validationErrorHandler(ConstraintViolationException e) {
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());

        e.getConstraintViolations().forEach(constraintViolation -> {
            errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
