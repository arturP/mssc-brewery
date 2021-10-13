package io.artur.spring.springboot.msscbrewery.services;

import io.artur.spring.springboot.msscbrewery.web.model.BeerDto;

import java.util.UUID;

/**
 *
 */
public interface BeerService {
    BeerDto getBeerById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDto);

    void updateBeerById(UUID beerId, BeerDto beerDto);

    void deleteById(UUID beerId);
}
