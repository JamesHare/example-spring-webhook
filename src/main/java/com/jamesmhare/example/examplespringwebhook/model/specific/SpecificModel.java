package com.jamesmhare.example.examplespringwebhook.model.specific;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jamesmhare.example.examplespringwebhook.model.specific.validation.ValidMovieGenres;
import com.jamesmhare.example.examplespringwebhook.model.specific.validation.ValidOfficeLocation;
import com.jamesmhare.example.examplespringwebhook.model.generic.GenericModel;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * A pojo class to represent a specific data model.
 *
 * The idea is that the {@link GenericModel} can be used if anything changes over time with the fields on this specific model.
 * When there is a change, the project code would have to be updated to reflect the new model. However, with the generic model,
 * we can send the incoming raw payload with the generic model to be processed downstream without having to make changes
 * to this project.
 *
 * @author James Hare
 */
public class SpecificModel {

    @NotNull(message = "First name must be included in payload")
    private String firstName;
    @NotNull(message = "Last name must be included in payload")
    private String lastName;
    private int age;
    private String gender;
    @ValidMovieGenres
    private Map<String, String> favoriteMoviesToGenres;
    @ValidOfficeLocation
    private List<String> workLocations;

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

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

    public Map<String, String> getFavoriteMoviesToGenres() {
        return favoriteMoviesToGenres;
    }

    public void setFavoriteMoviesToGenres(Map<String, String> favoriteMoviesToGenres) {
        this.favoriteMoviesToGenres = favoriteMoviesToGenres;
    }

    public List<String> getWorkLocations() {
        return workLocations;
    }

    public void setWorkLocations(List<String> workLocations) {
        this.workLocations = workLocations;
    }

    @Override
    public String toString() {
        return "SpecificModel{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", favoriteMoviesToGenres=" + favoriteMoviesToGenres +
                ", workLocations=" + workLocations +
                '}';
    }
}
