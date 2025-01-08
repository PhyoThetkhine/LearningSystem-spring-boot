package com.system.Learning_system_springboot_angular.model.exception;

public class InvalidField {
    private final String fieldName;
    private final String message;

    public InvalidField(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}