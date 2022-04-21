package com.dh.catalogservice.domain.repository;

import com.dh.catalogservice.domain.dto.MovieWS;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieFeignRepository {
    @GetMapping("/movies/{genre}")
    List<MovieWS> getMoviesByGenre(@PathVariable String genre);
}
