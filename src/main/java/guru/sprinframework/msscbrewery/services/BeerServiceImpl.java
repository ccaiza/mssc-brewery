/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services;

import guru.sprinframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getBeerById(UUID beerId) {
       return BeerDto.builder().id(UUID.randomUUID())
               .beerName("Galaxy Cat")
               .beerStyle("Pale Ale")
               .build();
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return BeerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {
        //TODO se debe terminar la implementacion
    }

    @Override
    public void deleteById(UUID beerId) {
        log.debug("Eliminado beer ....");
    }
    
}
