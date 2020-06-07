package com.jamesmhare.example.examplespringwebhook.service;

import com.jamesmhare.example.examplespringwebhook.model.generic.GenericModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class WebhookServiceTest {

    @Configuration
    static class WebhookServiceTestContextConfiguration {
        @Bean
        public WebhookService service() {
            return new WebhookService();
        }
    }

    @Autowired
    WebhookService service;

    @Test
    public void testSendDownstream() throws Exception {
        service.sendDownstream(mock(GenericModel.class));
    }

}
