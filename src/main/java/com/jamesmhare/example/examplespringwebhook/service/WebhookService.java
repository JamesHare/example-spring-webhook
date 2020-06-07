package com.jamesmhare.example.examplespringwebhook.service;

import com.jamesmhare.example.examplespringwebhook.model.generic.GenericModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * A Service to process payloads and send them downstream.
 *
 * @author James Hare
 */
@Service
public class WebhookService {

    private final Logger log = LoggerFactory.getLogger(WebhookService.class);

    /**
     * Sends a generic payload downstream.
     *
     * @param payload a generic payload
     * @throws Exception
     */
    public void sendDownstream(final GenericModel payload) throws Exception {
        log.info("Sending payload downstream: {}", payload.toString());
        // whateverClient.send(payload);
    }

}
