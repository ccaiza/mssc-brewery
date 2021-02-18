/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.services.v2;

import guru.sprinframework.msscbrewery.web.model.v2.BeerDtoV2;
import java.util.UUID;

/**
 *
 * @author Carlos
 */
public interface BeerServiceV2 {

    public BeerDtoV2 getBeerById(UUID beerId);
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto);

    public void updateBeer(UUID beerId, BeerDtoV2 beerDto);

    public void deleteById(UUID beerId);
    
}
