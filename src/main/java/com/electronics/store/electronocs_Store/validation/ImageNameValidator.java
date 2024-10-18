package com.electronics.store.electronocs_Store.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class ImageNameValidator implements ConstraintValidator<ImageNameValidation,String> {
private Logger logger = LoggerFactory.getLogger(ImageNameValidator.class);
    @Override
    public void initialize(ImageNameValidation constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
      //logic

        if (s.isBlank())
            return false;
        else
            return true;


    }
}
