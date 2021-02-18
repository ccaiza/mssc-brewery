/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services;

import guru.sprinframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
       return BeerDto.builder().id(UUID.randomUUID())
               .beerName("Galaxy Cat")
               .beerStyle("Pale Ale")
               .build();
    }
    
}
