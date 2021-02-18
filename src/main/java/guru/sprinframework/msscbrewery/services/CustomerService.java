/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services;

import guru.sprinframework.msscbrewery.web.model.CustomerDto;
import java.util.UUID;

/**
 *
 * @author Carlos
 */
public interface CustomerService {
    
     /**
     *
     * @param customerId
     * @return
     */
    public CustomerDto getCustomerById(UUID customerId);
}
