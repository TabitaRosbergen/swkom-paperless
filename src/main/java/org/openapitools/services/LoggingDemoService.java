package org.openapitools.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LoggingDemoService {

    public void doSomething() {
        log.info("do something.");
    }

}
