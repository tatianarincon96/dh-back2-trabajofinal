package com.dh.catalogservice.api.service.impl;

import com.dh.catalogservice.api.controller.CatalogController;
import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.dto.CatalogWS;
import com.dh.catalogservice.domain.dto.MovieWS;
import com.dh.catalogservice.domain.dto.SerieWS;
import com.dh.catalogservice.domain.repository.CatalogRepository;
import com.dh.catalogservice.domain.repository.MovieFeignRepository;
import com.dh.catalogservice.domain.repository.SequenceGeneratorService;
import com.dh.catalogservice.domain.repository.SerieFeignRepository;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CatalogServiceImpl implements CatalogService {
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
    private final MovieFeignRepository movieFeignRepository;
    private final SerieFeignRepository serieFeignRepository;
    private final CatalogRepository catalogRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    public CatalogServiceImpl(MovieFeignRepository movieFeignRepository, SerieFeignRepository serieFeignRepository, CatalogRepository catalogRepository, SequenceGeneratorService sequenceGeneratorService) {
        this.movieFeignRepository = movieFeignRepository;
        this.serieFeignRepository = serieFeignRepository;
        this.catalogRepository = catalogRepository;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @CircuitBreaker(name = "movies", fallbackMethod = "findMoviesEmptyFallback")
    @Retry(name = "movies")
    private List<MovieWS> getMoviesByGenre(String genre) {
        return movieFeignRepository.getMoviesByGenre(genre);
    }

    @CircuitBreaker(name = "series", fallbackMethod = "findSeriesEmptyFallback")
    @Retry(name = "series")
    private List<SerieWS> getSeriesByGenre(String genre) {
        return serieFeignRepository.getSeriesByGenre(genre);
    }

    @Override
    public void updateCatalogByGenre(String genre) {
        List<MovieWS> listOfMoviesByGenre = getMoviesByGenre(genre);
        List<SerieWS> listOfSeriesByGenre = getSeriesByGenre(genre);
        CatalogWS catalog = catalogRepository.findByGenre(genre).orElse(null);
        if (catalog != null) {
            catalog.setMovies(listOfMoviesByGenre);
            catalog.setSeries(listOfSeriesByGenre);
            catalogRepository.save(catalog);
        } else {
            catalogRepository.save(CatalogWS.builder()
                    .id(sequenceGeneratorService.generateSequence(CatalogWS.SEQUENCE_NAME))
                    .genre(genre)
                    .movies(listOfMoviesByGenre)
                    .series(listOfSeriesByGenre)
                    .build());
        }
    }

    public CatalogWS getCatalogByGenre(String genre) {
        return catalogRepository.findByGenre(genre).orElseThrow();
    }

    private List<MovieWS> findMoviesEmptyFallback(CallNotPermittedException ex) {
        log.info("Circuit breaker activated for movies-service");
        return new ArrayList<>();
    }

    private List<SerieWS> findSeriesEmptyFallback(CallNotPermittedException ex) {
        log.info("Circuit breaker activated for series-service");
        return new ArrayList<>();
    }
}
