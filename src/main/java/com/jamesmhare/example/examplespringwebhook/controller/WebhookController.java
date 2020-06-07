package com.jamesmhare.example.examplespringwebhook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesmhare.example.examplespringwebhook.model.generic.GenericModel;
import com.jamesmhare.example.examplespringwebhook.model.generic.GenericModelResolver;
import com.jamesmhare.example.examplespringwebhook.model.specific.SpecificModel;
import com.jamesmhare.example.examplespringwebhook.service.WebhookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Main controller for the web service.
 *
 * @author James Hare
 */
@Validated
@Controller
public class WebhookController {

    private static final Logger log = LoggerFactory.getLogger(WebhookController.class);
    private static final ResponseEntity<String> okResponse = new ResponseEntity<>("Payload was successfully processed.", HttpStatus.OK);

    @Autowired
    private WebhookService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Validator validator;

    /**
     * Handles post requests containing a generic model payload.
     *
     * @param body the payload
     * @return a response
     */
    @PostMapping(value = "/v1/payload/generic", consumes = "application/json")
    public ResponseEntity<String> addGenericPayload(@RequestBody final String body) {
        log.info("Received generic payload: {} ", body);
        try {
            final GenericModel genericPayload = objectMapper.readValue(body, GenericModel.class);
            service.sendDownstream(genericPayload);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return okResponse;
    }

    /**
     * Handles post requests containing a specific model payload. The incoming payload will be
     * converted the a generic model, with a field containing the full raw incoming payload before
     * being sent downstream.
     *
     * @param body the payload
     * @return a response
     */
    @PostMapping(value = "/v1/payload/specific", consumes = "application/json")
    public ResponseEntity<String> addSpecificPayload(@RequestBody final String body) {
        log.info("Received specific data model payload: {} ", body);
        try {
            final SpecificModel specificPayload = objectMapper.readValue(body, SpecificModel.class);

            // validate the required fields
            final Set<ConstraintViolation<SpecificModel>> violations = validator.validate(specificPayload);
            if (!violations.isEmpty()) {
                final StringBuilder allViolations = new StringBuilder("Found the following violations: ");
                for (final ConstraintViolation<SpecificModel> violation : violations) {
                    log.error(violation.getMessage());
                    allViolations.append(violation.getMessage()).append(", ");
                }
                return new ResponseEntity<>(allViolations.toString(), HttpStatus.BAD_REQUEST);
            }

            GenericModel genericModel = GenericModelResolver.convertFromSpecificModel(specificPayload, objectMapper.readTree(body));
            service.sendDownstream(genericModel);
        } catch (Exception e) {
            log.error(e.getMessage(), e.getCause());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
        }
        return okResponse;
    }

}
