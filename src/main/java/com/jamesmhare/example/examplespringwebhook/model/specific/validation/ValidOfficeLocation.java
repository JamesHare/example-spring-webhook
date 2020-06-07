package com.jamesmhare.example.examplespringwebhook.model.specific.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = { OfficeLocationValidator.class })
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidOfficeLocation {

    String message() default "Contains an invalid office location";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}