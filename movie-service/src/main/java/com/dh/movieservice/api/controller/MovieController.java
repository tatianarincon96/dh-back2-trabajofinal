package com.dh.movieservice.api.controller;

import com.dh.movieservice.api.queue.MessageSender;
import com.dh.movieservice.api.service.MovieService;
import com.dh.movieservice.domain.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
	private final MovieService movieService;
	private final MessageSender messageSender;
	private final Logger logger = LoggerFactory.getLogger(MovieController.class);

	@Autowired
	public MovieController(MovieService movieService, MessageSender messageSender) {
		this.movieService = movieService;
		this.messageSender = messageSender;
	}

	@GetMapping("/{genre}")
	public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre) {
		logger.info("Getting list of movies by genre {}", genre);
		return ResponseEntity.ok().body(movieService.getListByGenre(genre));
	}

	@PostMapping
	public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
		logger.info("Movie sent to rabbitmq and saved into database");
		messageSender.send(movie);
		return ResponseEntity.ok().body(movieService.save(movie));
	}
}
