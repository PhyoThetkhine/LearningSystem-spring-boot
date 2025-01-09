package com.system.Learning_system_springboot_angular.security;

import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        // Retrieve user by userCode from the database
        User user = userService.getByCode(userCode);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with code: " + userCode);
        }

        // Return a Spring Security User object
        return new org.springframework.security.core.userdetails.User(
                user.getUserCode(), // Use userCode as the username
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
