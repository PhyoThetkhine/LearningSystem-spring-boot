package com.system.Learning_system_springboot_angular.model.exception;

import java.util.ArrayList;
import java.util.List;

public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException(String message) {
        super(message);
    }
}