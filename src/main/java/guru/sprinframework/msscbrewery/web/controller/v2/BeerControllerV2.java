/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller.v2;

import guru.sprinframework.msscbrewery.services.v2.BeerServiceV2;
import guru.sprinframework.msscbrewery.web.model.v2.BeerDtoV2;
import java.util.UUID;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Carlos
 */
@RestController
@RequestMapping("/api/v2/beer")
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerService) {
        this.beerServiceV2 = beerService;
    }

    /**
     * Obtiene los datos de la cerveza
     *
     * @param beerId Codigo
     * @return Cerveza
     */
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable(name = "beerId") UUID beerId) {
        return new ResponseEntity<>(this.beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    /**
     * Ingresa una cerveza
     *
     * @param beerDto Entidad
     * @return Cerveza creada
     */
    @PostMapping
    public ResponseEntity handlePost(BeerDtoV2 beerDto) {
        BeerDtoV2 saveDto = this.beerServiceV2.saveNewBeer(beerDto);
        HttpHeaders headers = new HttpHeaders();
        // TODO hay que agregar el url completo
        headers.add("Location", "/api/v1/beer" + saveDto.getId().toString());
        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    /**
     * Actualiza los datos de un cerveza
     *
     * @param beerId
     * @param beerDto
     * @return
     */
    @PutMapping("/{beerId}")
    public ResponseEntity handleUpdate(@PathVariable(name = "beerId") UUID beerId, BeerDtoV2 beerDto) {
        this.beerServiceV2.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina un dato de cerveza. El metodo retorna el status vacio
     *
     * @param beerId
     */
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable(name = "beerId") UUID beerId) {
        this.beerServiceV2.deleteById(beerId);
    }
}
