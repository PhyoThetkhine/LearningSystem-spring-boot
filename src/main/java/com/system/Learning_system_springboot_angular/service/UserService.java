package com.system.Learning_system_springboot_angular.service;

import com.system.Learning_system_springboot_angular.model.dto.UserDTO;
import com.system.Learning_system_springboot_angular.model.entity.Role;

import java.util.List;

public interface UserService {
    public UserDTO saveUser(UserDTO dto);
    public String generateUserCode(Role role);
    public UserDTO getByCode(String code);
    public UserDTO getById(Integer id);
    public List<UserDTO> getAllUser();
    public void terminateUserById(Integer id);
    public void activeUserById(Integer id);
    public List<UserDTO> findStudentsNotEnrolledInCourse(Integer courseId);
    public List<UserDTO> findTeachersNotAssignedToCourse(Integer courseId);
    public void changePassword(String userCode, String currentPassword, String newPassword);
    public UserDTO updateByUser(UserDTO dto);
}
