package com.jamesmhare.example.examplespringwebhook.model.specific.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { MovieGenreValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMovieGenres {

    String message() default "Contains an invalid movie genre";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
