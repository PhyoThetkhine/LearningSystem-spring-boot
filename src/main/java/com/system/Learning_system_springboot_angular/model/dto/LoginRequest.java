package com.system.Learning_system_springboot_angular.model.dto;
public class LoginRequest {
    private String userCode;
    private String password;
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}
