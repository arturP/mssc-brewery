package io.artur.spring.springboot.msscbrewery.web.mappers;

import io.artur.spring.springboot.msscbrewery.model.Beer;
import io.artur.spring.springboot.msscbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

/**
 *
 */
@Mapper
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
