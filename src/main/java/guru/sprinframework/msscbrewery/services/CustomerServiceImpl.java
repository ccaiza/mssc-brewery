/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services;

import guru.sprinframework.msscbrewery.web.model.CustomerDto;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public CustomerDto getCustomerById(UUID customerId) {
       return CustomerDto.builder().id(UUID.randomUUID())
               .name("Carlos Caiza")
               .build();
    }
    
}
