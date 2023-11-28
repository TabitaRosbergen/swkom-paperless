package org.openapitools.services.requestservices;

import org.openapitools.configuration.RabbitMQConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class MessageService {
    private final RabbitTemplate rabbit;
    private final MinioService minioService;
    private final OCRService ocrService;
    private final Logger log = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    public MessageService(RabbitTemplate rabbit, MinioService minioService, OCRService ocrService) {
        this.rabbit = rabbit;
        this.minioService = minioService;
        this.ocrService = ocrService;
    }

    @RabbitListener(queues = RabbitMQConfig.MESSAGE_IN_QUEUE)
    public void receive(String path) throws IOException {
        log.info("MessageService received: '" + path + "'");
        System.out.println("MessageService received: '" + path + "'");

        //retrieve the document from minio
        File file = minioService.getDocument(path);

        //Print the document
        log.info("This is the file: " + file.getName());

        String result = ocrService.getFileContent(file);
        log.info("This is the result of the ocr service: " + result);
    }
}
