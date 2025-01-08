package com.system.Learning_system_springboot_angular.model.exception;


import java.util.List;

public class ErrorResponse {
    private String errorCode;
    private String message;
    private List<InvalidField> invalidFields; // For InvalidFieldsException

    // Constructors, getters, and setters
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(String errorCode, String message, List<InvalidField> invalidFields) {
        this.errorCode = errorCode;
        this.message = message;
        this.invalidFields = invalidFields;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<InvalidField> getInvalidFields() {
        return invalidFields;
    }

    public void setInvalidFields(List<InvalidField> invalidFields) {
        this.invalidFields = invalidFields;
    }
}