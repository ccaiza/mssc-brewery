/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services;

import guru.sprinframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;

/**
 *
 * @author Carlos
 */
public interface BeerService {
    
    /**
     *
     * @param beerId
     * @return
     */
    public BeerDto getBeerById(UUID beerId);
    
    public BeerDto saveNewBeer(BeerDto beerDto);

    public void updateBeer(UUID beerId, BeerDto beerDto);

    public void deleteById(UUID beerId);
}
