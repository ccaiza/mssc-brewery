/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guru.sprinframework.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sprinframework.msscbrewery.services.CustomerService;
import guru.sprinframework.msscbrewery.web.model.BeerDto;
import guru.sprinframework.msscbrewery.web.model.CustomerDto;
import java.util.UUID;
import javax.annotation.PostConstruct;
import static org.hamcrest.core.Is.is;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Carlos
 */
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    
    @MockBean
    CustomerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    CustomerDto validBeer;
    
    @PostConstruct
    public void setUp() {
        validBeer = CustomerDto.builder().id(UUID.randomUUID())
                .name("Customer Beer1")
                .build();
    }
    
    public CustomerControllerTest() {
    }

    /**
     * Test of getCustomer method, of class CustomerController.
     */
    @Test
    public void testGetCustomer() throws Exception {
        given(beerService.getCustomerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/customer/" + validBeer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(validBeer.getId().toString())))
                .andExpect(jsonPath("$.beerName", is("Beer1")));
    }

    /**
     * Test of handlePost method, of class CustomerController.
     */
    @Test
    public void testHandlePost() throws Exception{
        CustomerDto beerDto = validBeer;
        beerDto.setId(null);
        CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).name("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        given(beerService.saveNewCustomer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    /**
     * Test of handleUpdate method, of class CustomerController.
     */
    @Test
    public void testHandleUpdate() throws Exception{
        //given
        CustomerDto beerDto = validBeer;
        beerDto.setId(null);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        //when
        mockMvc.perform(put("/api/v1/customer/" + UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateCustomer(any(), any());
    }

//    /**
//     * Test of deleteBeer method, of class CustomerController.
//     */
//    @Test
//    public void testDeleteBeer() {
//    }
//
//    /**
//     * Test of validationErrorHandler method, of class CustomerController.
//     */
//    @Test
//    public void testValidationErrorHandler() {
//    }
    
}
