package com.dh.movieservice.api.queue;

import com.dh.movieservice.domain.model.Movie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSender {

    /* ===================== Attributes ===================== */

    private final RabbitTemplate rabbitTemplate;
    private final Queue movieQueue;
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    /* ===================== Constructors ===================== */

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, Queue movieQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.movieQueue = movieQueue;
    }

    /* ===================== Methods ===================== */

    public void send(Movie movie) {
        log.info("[SEND MESSAGE TO " + this.movieQueue.getName() + "] -> " + movie);
        this.rabbitTemplate.convertAndSend(this.movieQueue.getName(), movie);
    }


}
