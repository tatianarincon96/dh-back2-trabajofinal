package com.dh.serieservice.api.queue;

import com.dh.serieservice.domain.model.Serie;
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
    private final Queue catalogQueue;
    private static final Logger log = LoggerFactory.getLogger(MessageSender.class);

    /* ===================== Constructors ===================== */

    @Autowired
    public MessageSender(RabbitTemplate rabbitTemplate, Queue catalogQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.catalogQueue = catalogQueue;
    }

    /* ===================== Methods ===================== */

    public void send(Serie serie) {
        log.info("[SEND MESSAGE TO " + this.catalogQueue.getName() + "] -> " + serie);
        this.rabbitTemplate.convertAndSend(this.catalogQueue.getName(), serie);
    }


}
