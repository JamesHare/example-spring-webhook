package com.jamesmhare.example.examplespringwebhook.model.generic;

import com.fasterxml.jackson.databind.JsonNode;
import com.jamesmhare.example.examplespringwebhook.model.specific.SpecificModel;

/**
 * Provides a way to build a {@link GenericModel} object from various payloads.
 */
public class GenericModelResolver {

    public static GenericModel convertFromSpecificModel(final SpecificModel specificModel, final JsonNode body) {
        GenericModel genericModel = new GenericModel();
        genericModel.setFirstName(specificModel.getFirstName());
        genericModel.setLastName(specificModel.getLastName());
        genericModel.setAge(specificModel.getAge());
        genericModel.setGender(specificModel.getGender());
        genericModel.setRawPayload(body);
        return genericModel;
    }

}
