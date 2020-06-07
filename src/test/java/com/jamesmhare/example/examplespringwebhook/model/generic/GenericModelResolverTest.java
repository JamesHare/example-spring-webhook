package com.jamesmhare.example.examplespringwebhook.model.generic;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jamesmhare.example.examplespringwebhook.model.specific.SpecificModel;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;

public class GenericModelResolverTest {

    private static final String resourcePath = "./src/test/resources/json/";

    @Test
    public void testConvertFromSpecificModel() throws Exception {
        final String payload = FileUtils.readFileToString(new File(resourcePath + "SpecificModel_Valid.txt"), StandardCharsets.UTF_8);
        final ObjectMapper mapper = new ObjectMapper();
        final SpecificModel specificModel = mapper.readValue(payload, SpecificModel.class);
        final JsonNode rawPayload = mapper.readTree(payload);
        final GenericModel genericModel = GenericModelResolver.convertFromSpecificModel(specificModel, rawPayload);
        assertThat(specificModel, instanceOf(SpecificModel.class));
        assertEquals("James", genericModel.getFirstName());
        assertEquals("Hare", genericModel.getLastName());
        assertEquals(31, genericModel.getAge());
        assertEquals("Male", genericModel.getGender());
        assertEquals(rawPayload, genericModel.getRawPayload());
    }
}
