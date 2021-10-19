package io.artur.spring.springboot.msscbrewery.repositories;

import io.artur.spring.springboot.msscbrewery.model.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 *
 */
@Repository
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
