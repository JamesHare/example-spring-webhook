package com.jamesmhare.example.examplespringwebhook.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Pulls configuration from Spring Cloud Configuration server.
 *
 * @author James Hare
 */
public class ExampleSpringWebookConfig {

    private static final Logger log = LoggerFactory.getLogger(ExampleSpringWebookConfig.class);

    @Bean
    public Validator validator() {
        return Validation.buildDefaultValidatorFactory().getValidator();
    }

}
