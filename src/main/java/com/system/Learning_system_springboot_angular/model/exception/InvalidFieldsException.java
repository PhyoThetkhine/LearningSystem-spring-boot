package com.system.Learning_system_springboot_angular.model.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidFieldsException extends RuntimeException {
    private final List<InvalidField> invalidFields = new ArrayList<>();

    public InvalidFieldsException(String message) {
        super(message);
    }

    public void addField(InvalidField invalidField) {
        invalidFields.add(invalidField);
    }

    public boolean hasFields() {
        return !invalidFields.isEmpty();
    }

    public List<InvalidField> getInvalidFields() {
        return invalidFields;
    }
}