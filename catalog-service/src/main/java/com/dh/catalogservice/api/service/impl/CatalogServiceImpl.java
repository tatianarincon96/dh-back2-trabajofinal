package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.model.dto.CatalogWS;
import com.dh.catalogservice.domain.model.dto.MovieWS;
import com.dh.catalogservice.domain.repository.MovieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogServiceImpl implements CatalogService {
	private MovieFeignRepository movieFeignRepository;

	@Autowired
	public CatalogServiceImpl(MovieFeignRepository movieFeignRepository) {
		this.movieFeignRepository = movieFeignRepository;
	}

	@Override
	@CircuitBreaker(name = "movies", fallbackMethod = "getMovieFallbackValue")
	@Retry(name = "movies")
	public CatalogWS getMoviesCatalogByGenre(String genre) {
		List<MovieWS> listOfMoviesByGenre = movieFeignRepository.getMoviesByGenre(genre);
		return CatalogWS.builder().genre(genre).movies(listOfMoviesByGenre).build();
	}

	private void getSubscriptionFallbackValue(CallNotPermittedException ex) throws Exception {
		throw new Exception("Circuit breaker was activated");
	}
}
