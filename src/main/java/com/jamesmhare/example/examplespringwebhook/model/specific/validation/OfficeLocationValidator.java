package com.jamesmhare.example.examplespringwebhook.model.specific.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class OfficeLocationValidator implements ConstraintValidator<ValidOfficeLocation, List<String>> {

    private final String[] validOfficeLocations = new String[]{"Orlando", "Nashville", "Chattanooga"};

    @Override
    public boolean isValid(List<String> workLocations, ConstraintValidatorContext constraintValidatorContext) {
        final StringBuilder sb = new StringBuilder();
        for (String location : workLocations) {
            if (!Arrays.asList(validOfficeLocations).contains(location)){
                sb.append(location).append(" is not a valid office location, ");
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
