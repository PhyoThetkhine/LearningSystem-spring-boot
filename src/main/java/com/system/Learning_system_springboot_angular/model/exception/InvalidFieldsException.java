package com.system.Learning_system_springboot_angular.model.exception;
public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException(String message) {
        super(message);
    }
}