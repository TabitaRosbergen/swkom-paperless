package org.openapitools.configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE = "";

    public static final String MESSAGE_IN_QUEUE = "Echo_In";
    public static final String MESSAGE_OUT_QUEUE = "Echo_Out";

    //public static final String ECHO_MESSAGE_COUNT_PROPERTY_NAME = "MessageCount";

    @Bean
    public Queue echoInQueue() {
        return new Queue(MESSAGE_IN_QUEUE, false);
    }

    @Bean
    public Queue echoOutQueue() {
        return new Queue(MESSAGE_OUT_QUEUE, false);
    }

    @Bean
    public static ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("swkom-paperless-rabbitmq-1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setDefaultReceiveQueue(MESSAGE_IN_QUEUE);
        return rabbitTemplate;
    }

}
