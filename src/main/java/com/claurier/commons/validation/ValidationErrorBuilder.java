package com.claurier.commons.validation;

import org.springframework.validation.Errors;

public class ValidationErrorBuilder {

    public static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError(errors.getGlobalErrors(), errors.getFieldErrors());
        return error;
    }
}
