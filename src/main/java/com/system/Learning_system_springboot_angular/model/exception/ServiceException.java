package com.system.Learning_system_springboot_angular.model.exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {}

    public ServiceException(String message) {
        super(message);
    }
}
