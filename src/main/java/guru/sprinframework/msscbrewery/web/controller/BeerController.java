/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller;

import guru.sprinframework.msscbrewery.services.BeerService;
import guru.sprinframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {
    
    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }
    
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable(name = "beerId")  UUID beerId){
        return new ResponseEntity<>(this.beerService.getBeerById(beerId),HttpStatus.OK);
    }
}
