package org.openapitools;

import org.junit.jupiter.api.Test;
import org.openapitools.services.LoggingDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class OpenApiGeneratorApplicationTests {

    @Autowired
    private LoggingDemoService loggingDemoService;

    @Test
    void contextLoads() {
    }

    @Test
    void testLogging() {
        loggingDemoService.doSomething();
    }

}