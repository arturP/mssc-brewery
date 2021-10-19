package io.artur.spring.springboot.msscbrewery.web.controller;

import io.artur.spring.springboot.msscbrewery.model.Beer;
import io.artur.spring.springboot.msscbrewery.repositories.BeerRepository;
import io.artur.spring.springboot.msscbrewery.web.mappers.BeerMapper;
import io.artur.spring.springboot.msscbrewery.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

/**
 *
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @GetMapping({"/{beerId}"})
    public ResponseEntity<BeerDto> getBeer(@NotNull @PathVariable UUID beerId) {

        BeerDto beerDto = beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElse(null));

        return new ResponseEntity<>(beerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto) {

        Beer savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @DeleteMapping({"/{beerId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("beerId") UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
