package org.openapitools.services.requestservices;

import org.openapitools.configuration.RabbitMQConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private final RabbitTemplate rabbit;
    Logger log = LoggerFactory.getLogger(MessageService.class);
    @Autowired
    public MessageService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @RabbitListener(queues = RabbitMQConfig.MESSAGE_IN_QUEUE)
    public void receive(String in) {
        log.info("MessageService received: '" + in + "'");
        System.out.println("MessageService received: '" + in + "'");
    }
}
