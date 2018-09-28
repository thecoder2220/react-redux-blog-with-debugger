package com.claurier.commons.validation;

import java.util.List;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationError {
    private final List<ObjectError> globalErrors;
    private final List<FieldError> fieldErrors;

    public ValidationError(List<ObjectError> globalErrors, List<FieldError> fieldErrors) {
        this.globalErrors = globalErrors;
        this.fieldErrors = fieldErrors;
    }

    public List<ObjectError> getGlobalErrors() {
        return this.globalErrors;
    }

    public List<FieldError> getFieldErrors() {
        return this.fieldErrors;
    }
}
