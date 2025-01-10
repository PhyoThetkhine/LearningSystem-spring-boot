package com.system.Learning_system_springboot.controller;
import com.system.Learning_system_springboot.model.dto.UserDTO;
import com.system.Learning_system_springboot.model.dto.ApiResponse;
import com.system.Learning_system_springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(user);
        ApiResponse<UserDTO> response = ApiResponse.success(HttpStatus.OK.value(), "User created successfully", savedUser);
        return ResponseEntity.ok(response);
    }
}