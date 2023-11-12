package org.openapitools.services.requestservices;

import org.openapitools.configuration.RabbitMQConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private final RabbitTemplate rabbit;

    @Autowired
    public MessageService(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }

    @RabbitListener(queues = RabbitMQConfig.ECHO_IN_QUEUE_NAME)
    public void receive(String in) {
        System.out.println("MessageService received: '" + in + "'");
    }
}