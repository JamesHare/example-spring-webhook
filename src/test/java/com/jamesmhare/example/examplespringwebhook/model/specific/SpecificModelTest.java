package com.jamesmhare.example.examplespringwebhook.model.specific;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class SpecificModelTest {

    private static final String TEST = "test";

    @Test
    public void testSetFirstName() {
        SpecificModel specificModel = new SpecificModel();
        specificModel.setFirstName(TEST);
        assertEquals(TEST, specificModel.getFirstName());
    }

    @Test
    public void testSetLastName() {
        SpecificModel specificModel = new SpecificModel();
        specificModel.setLastName(TEST);
        assertEquals(TEST, specificModel.getLastName());
    }

    @Test
    public void testSetAge() {
        SpecificModel specificModel = new SpecificModel();
        specificModel.setAge(1);
        assertEquals(1, specificModel.getAge());
    }

    @Test
    public void testSetGender() {
        SpecificModel specificModel = new SpecificModel();
        specificModel.setGender(TEST);
        assertEquals(TEST, specificModel.getGender());
    }

    @Test
    public void testSetFavoriteMoviesToGenres() throws Exception {
        Map<String, String> mockFavoriteMoviesToGenres = mock(Map.class);
        SpecificModel specificModel = new SpecificModel();
        specificModel.setFavoriteMoviesToGenres(mockFavoriteMoviesToGenres);
        assertEquals(mockFavoriteMoviesToGenres, specificModel.getFavoriteMoviesToGenres());
    }

    @Test
    public void testSetWorkLocations() throws Exception {
        List<String> mockWorkLocations = mock(List.class);
        SpecificModel specificModel = new SpecificModel();
        specificModel.setWorkLocations(mockWorkLocations);
        assertEquals(mockWorkLocations, specificModel.getWorkLocations());
    }
}
