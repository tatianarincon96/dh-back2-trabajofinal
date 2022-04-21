package com.dh.catalogservice.api.queue;

import com.dh.catalogservice.api.service.CatalogService;
import com.dh.catalogservice.domain.dto.MovieWS;
import com.dh.catalogservice.domain.dto.SerieWS;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {
    private static final Logger log = LoggerFactory.getLogger(MessageListener.class);
    private CatalogService catalogService;
     // =========================== Methods ===========================

    @Autowired
    public MessageListener(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @RabbitListener(queues = {"${queue.movie.name}"})
    public void receiveMessage(@Payload MovieWS movie) {
        log.info("[MESSAGE RECEIVE FROM MOVIE] -> " + movie);
        catalogService.updateCatalogByGenre(movie.getGenre());
    }

    @RabbitListener(queues = {"${queue.serie.name}"})
    public void receiveMessage(@Payload SerieWS serie) {
        log.info("[MESSAGE RECEIVE FROM SERIE] -> " + serie);
        catalogService.updateCatalogByGenre(serie.getGenre());
    }
}
