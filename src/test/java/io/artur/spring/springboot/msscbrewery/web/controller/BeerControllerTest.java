package io.artur.spring.springboot.msscbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.artur.spring.springboot.msscbrewery.services.BeerService;
import io.artur.spring.springboot.msscbrewery.web.model.BeerDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 *
 */
@WebAppConfiguration()
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
class BeerControllerTest {

    @Mock
    BeerService beerService;

    @InjectMocks
    BeerController beerController;

    MockMvc mockMvc;

    //@Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    private BeerDto beerDto;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(beerController).build();

        beerDto = BeerDto.builder()
                .id(UUID.randomUUID())
                .beerName("Lomza")
                .beerStyle("Pils")
                .upc(12321L)
                .build();
    }

    @Test
    void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(beerDto);

        mockMvc.perform(get("/api/v1/beer/" + beerDto.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(beerDto.getId().toString())))
                .andExpect(jsonPath("$.beerName", is(beerDto.getBeerName())));
    }

    @Test
    void handlePost() throws Exception {
        BeerDto beerDtoNew = beerDto;
        beerDtoNew.setId(null);
        BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
        String beerDtoJson = objectMapper.writeValueAsString(beerDtoNew);

        given(beerService.saveNewBeer(any())).willReturn(savedDto);

        mockMvc.perform(post("/api/v1/beer/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isCreated());
    }

    @Test
    void handleUpdate() throws Exception {
        //given
        BeerDto beerDtoNew = beerDto;
        String beerDtoJson = objectMapper.writeValueAsString(beerDtoNew);

        //when
        mockMvc.perform(put("/api/v1/beer/" + beerDto.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(beerDtoJson))
                .andExpect(status().isNoContent());

        then(beerService).should().updateBeerById(any(), any());
    }
}