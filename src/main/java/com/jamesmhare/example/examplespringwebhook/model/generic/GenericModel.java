package com.jamesmhare.example.examplespringwebhook.model.generic;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import com.jamesmhare.example.examplespringwebhook.model.specific.SpecificModel;

/**
 * A pojo class to represent a generic data model.
 *
 * The idea is that this model can be used if anything changes over time with the fields on the {@link SpecificModel}. When
 * there is a change, the project code would have to be updated to reflect the new model. However, with this generic model,
 * we can send the incoming raw payload with this generic model to be processed downstream without having to make changes
 * to this project.
 *
 * @author James Hare
 */
public class GenericModel {

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private JsonNode rawPayload;

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public JsonNode getRawPayload() {
        return rawPayload;
    }

    public void setRawPayload(JsonNode rawPayload) {
        this.rawPayload = rawPayload;
    }

    @Override
    public String toString() {
        return "GenericModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", rawPayload=" + rawPayload +
                '}';
    }
}
