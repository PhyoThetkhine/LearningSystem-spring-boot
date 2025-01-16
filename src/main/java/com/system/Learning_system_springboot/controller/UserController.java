package com.system.Learning_system_springboot.controller;
import com.system.Learning_system_springboot.model.dto.UserDTO;
import com.system.Learning_system_springboot.model.dto.ApiResponse;
import com.system.Learning_system_springboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/save")
    public ResponseEntity<?> createUser(@RequestBody UserDTO user) {
        UserDTO savedUser = userService.saveUser(user);
        ApiResponse<UserDTO> response = ApiResponse.success(HttpStatus.OK.value(), "User created successfully", savedUser);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
        UserDTO user = userService.getById(id);
        ApiResponse<UserDTO> response = ApiResponse.success(HttpStatus.OK.value(), "User fetched successfully", user);
        return ResponseEntity.ok(response);
    }

    @GetMapping("users") //GET http://localhost:8080/api/user/users?page=0&size=20&sortBy=id  the index of page start from 0 to .....
    public ResponseEntity<?> getUsersPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        sortBy = sortBy.trim();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<UserDTO> users = userService.getAllUser(pageable);
        ApiResponse<Page<UserDTO>> response = ApiResponse.success(HttpStatus.OK.value(), "Users List", users);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        UserDTO updatedUser = userService.updateByUser(user);
        ApiResponse<UserDTO> response = ApiResponse.success(HttpStatus.OK.value(), "User updated successfully", updatedUser);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) {
        userService.terminateUserById(id);
        ApiResponse<Void> response = ApiResponse.success(HttpStatus.OK.value(), "User deleted successfully", null);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/activate/{id}")
    public ResponseEntity<?> activateUser(@PathVariable Integer id) {
        userService.activeUserById(id);
        ApiResponse<Void> response = ApiResponse.success(HttpStatus.OK.value(), "User activated successfully", null);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/students-not-enroll")
    public ResponseEntity<?> getStudentsNotEnrolledInCourse(@RequestParam Integer courseId) {
        List<UserDTO> students = userService.findStudentsNotEnrolledInCourse(courseId);
        ApiResponse<List<UserDTO>> response = ApiResponse.success(HttpStatus.OK.value(), "Students Not Enrolled", students);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/teachers-not-assigned")
    public ResponseEntity<?> getTeachersNotAssignedToCourse(@RequestParam Integer courseId) {
        List<UserDTO> teachers = userService.findTeachersNotAssignedToCourse(courseId);
        ApiResponse<List<UserDTO>> response = ApiResponse.success(HttpStatus.OK.value(), "Teachers Not Assigned", teachers);
        return ResponseEntity.ok(response);
    }
}
