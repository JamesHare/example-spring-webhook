package com.jamesmhare.example.examplespringwebhook.model.generic;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class GenericModelTest {

    private static final String TEST = "test";

    @Test
    public void testSetFirstName() {
        GenericModel genericModel = new GenericModel();
        genericModel.setFirstName(TEST);
        assertEquals(TEST, genericModel.getFirstName());
    }

    @Test
    public void testSetLastName() {
        GenericModel genericModel = new GenericModel();
        genericModel.setLastName(TEST);
        assertEquals(TEST, genericModel.getLastName());
    }

    @Test
    public void testSetAge() {
        GenericModel genericModel = new GenericModel();
        genericModel.setAge(1);
        assertEquals(1, genericModel.getAge());
    }

    @Test
    public void testSetGender() {
        GenericModel genericModel = new GenericModel();
        genericModel.setGender(TEST);
        assertEquals(TEST, genericModel.getGender());
    }

    @Test
    public void testGetRawPayload() {
        JsonNode rawPayload = mock(JsonNode.class);
        GenericModel genericModel = new GenericModel();
        genericModel.setRawPayload(rawPayload);
        assertEquals(rawPayload, genericModel.getRawPayload());
    }
}
