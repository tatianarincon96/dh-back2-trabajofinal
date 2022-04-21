package com.dh.serieservice.api.controller;

import com.dh.serieservice.api.queue.MessageSender;
import com.dh.serieservice.api.service.SerieService;
import com.dh.serieservice.domain.model.Serie;
import com.dh.serieservice.domain.repository.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController {
    private final SerieService serieService;
    private final MessageSender messageSender;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final Logger logger = LoggerFactory.getLogger(SerieController.class);

    @Autowired
    public SerieController(SerieService serieService, MessageSender messageSender, SequenceGeneratorService sequenceGeneratorService) {
        this.serieService = serieService;
        this.messageSender = messageSender;
        this.sequenceGeneratorService = sequenceGeneratorService;
    }

    @GetMapping("/{genre}")
    public ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre) {
        logger.info("Getting list of series by genre {}", genre);
        return ResponseEntity.ok().body(serieService.getListByGenre(genre));
    }

    @PostMapping
    public ResponseEntity<Serie> saveSerie(@RequestBody Serie serie) {
        logger.info("Serie sent to rabbitmq and saved into database");
        messageSender.send(serie);
        serie.setId(sequenceGeneratorService.generateSequence(Serie.SEQUENCE_NAME));
        return ResponseEntity.ok().body(serieService.save(serie));
    }
}
