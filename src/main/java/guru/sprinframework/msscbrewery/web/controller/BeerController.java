/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller;

import guru.sprinframework.msscbrewery.services.BeerService;
import guru.sprinframework.msscbrewery.web.model.BeerDto;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Carlos
 * @since 1
 * @deprecated Se debe emplear /api/v2/beer
 */
@Deprecated(forRemoval = true,since = "1")
@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    /**
     * Obtiene los datos de la cerveza
     *
     * @param beerId Codigo
     * @return Cerveza
     */
    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable(name = "beerId") UUID beerId) {
        return new ResponseEntity<>(this.beerService.getBeerById(beerId), HttpStatus.OK);
    }

    /**
     * Ingresa una cerveza
     *
     * @param beerDto Entidad
     * @return Cerveza creada
     */
    @PostMapping
    public ResponseEntity handlePost(@Valid @RequestBody BeerDto beerDto) {
        BeerDto saveDto = this.beerService.saveNewBeer(beerDto);
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
    public ResponseEntity handleUpdate(@PathVariable(name = "beerId") UUID beerId,
            @Valid @RequestBody BeerDto beerDto) {
        this.beerService.updateBeer(beerId, beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    /**
     * Elimina un dato de cerveza. El metodo retorna el status vacio
     *
     * @param beerId
     */
    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void deleteBeer(@PathVariable(name = "beerId") UUID beerId) {
        this.beerService.deleteById(beerId);
    }
}
