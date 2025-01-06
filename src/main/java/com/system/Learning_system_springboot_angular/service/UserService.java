package com.system.Learning_system_springboot_angular.service;

import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        String userCode;
        userCode = generateUserCode(user.getRole().name());
        user.setUserCode(userCode);
        return userRepository.save(user);
    }

    private String generateUserCode(String roleName) {
        String prefix = switch (roleName) {
            case "ADMIN"  -> "ADM";
            case "TEACHER" -> "TEA";
            case "STUDENT" -> "STU";
            default -> "USR";
        };
        Long count = userRepository.countByRoleName(roleName);
        return prefix + String.format("%04d", count + 1);
    }

}
