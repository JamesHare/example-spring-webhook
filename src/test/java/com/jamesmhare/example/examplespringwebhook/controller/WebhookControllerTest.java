package com.jamesmhare.example.examplespringwebhook.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesmhare.example.examplespringwebhook.service.WebhookService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.validation.Validation;
import javax.validation.Validator;
import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WebhookControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    private static final String resourcePath = "./src/test/resources/json/";

    @Configuration
    static class WebhookControllerTestContextConfiguration {
        @Bean
        public Validator validator() {
            return Validation.buildDefaultValidatorFactory().getValidator();
        }
    }

    @Mock
    WebhookService mockService;
    @Spy
    ObjectMapper objectMapper;
    @Autowired
    @Spy
    Validator validator;

    private MockMvc mockMvc;

    @InjectMocks
    private WebhookController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testAddGenericPayload_validJson() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "GenericModel_Valid.txt"), StandardCharsets.UTF_8);
        mockMvc.perform(post("/v1/payload/generic").contentType(APPLICATION_JSON_UTF8).content(payload)).andExpect(status().isOk())
                .andExpect(content().string("Payload was successfully processed."));
    }

    @Test
    public void testAddGenericPayload_nullJson() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "NullJson.txt"), StandardCharsets.UTF_8);
        mockMvc.perform(post("/v1/payload/generic").contentType(APPLICATION_JSON_UTF8).content(payload)).andExpect(status().isBadRequest());
    }

    @Test
    public void testAddSpecificPayload_validJson() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "SpecificModel_Valid.txt"), StandardCharsets.UTF_8);
        mockMvc.perform(post("/v1/payload/specific").contentType(APPLICATION_JSON_UTF8).content(payload)).andExpect(status().isOk())
                .andExpect(content().string("Payload was successfully processed."));
    }

    @Test
    public void testAddSpecificPayload_nullJson() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "NullJson.txt"), StandardCharsets.UTF_8);
        mockMvc.perform(post("/v1/payload/specific").contentType(APPLICATION_JSON_UTF8).content(payload)).andExpect(status().isBadRequest());
    }

    @Test
    public void testAddSpecificPayload_invalidFields() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "SpecificModel_InvalidFields.txt"), StandardCharsets.UTF_8);
        final MvcResult result = mockMvc.perform(post("/v1/payload/specific").contentType(APPLICATION_JSON_UTF8).content(payload)).andExpect(status().isBadRequest()).andReturn();
        final String resultContent = result.getResponse().getContentAsString();

        // List of missing fields may not always be in the same order in response
        assertTrue(resultContent.contains("Found the following violations"));
        assertTrue(resultContent.contains("First name must be included in payload"));
        assertTrue(resultContent.contains("Last name must be included in payload"));
        assertTrue(resultContent.contains("Contains an invalid office location"));
        assertTrue(resultContent.contains("New York is not a valid office location"));
        assertTrue(resultContent.contains("Seattle is not a valid office location"));
        assertTrue(resultContent.contains("New Orleans is not a valid office location"));
        assertTrue(resultContent.contains("Contains an invalid movie genre"));
        assertTrue(resultContent.contains("Movie genre independent not recognized"));
        assertTrue(resultContent.contains("Movie genre sports not recognized"));
        assertTrue(resultContent.contains("Movie genre musicals not recognized"));
    }

}
