package com.electronics.store.electronocs_Store.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValidation {
    //error message
    String message() default "Invalid Image Name !!";

    //represent group of constraints
    Class<?>[] groups() default {};

    //additional information about annotation
    Class<? extends Payload>[] payload() default {};
}
