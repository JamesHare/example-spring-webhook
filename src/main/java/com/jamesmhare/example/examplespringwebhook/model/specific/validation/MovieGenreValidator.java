package com.jamesmhare.example.examplespringwebhook.model.specific.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Map;

@Component
public class MovieGenreValidator implements ConstraintValidator<ValidMovieGenres, Map<String, String>> {

    private final String[] validGenres = new String[]{"horror", "family", "scifi", "romance", "crime", "thriller"};

    @Override
    public boolean isValid(final Map<String, String> favoriteMoviesToGenres, ConstraintValidatorContext constraintValidatorContext) {
        final StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : favoriteMoviesToGenres.entrySet()) {
            if (!Arrays.asList(validGenres).contains(entry.getValue())){
                sb.append("Movie genre ").append(entry.getValue()).append(" not recognized, ");
            }
        }
        if (!sb.toString().isEmpty()) {
            sb.setLength(sb.length() - 2);
            constraintValidatorContext.buildConstraintViolationWithTemplate(sb.toString()).addConstraintViolation();
            return false;
        }
        return true;
    }
}
