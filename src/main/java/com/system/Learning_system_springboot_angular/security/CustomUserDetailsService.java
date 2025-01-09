package com.system.Learning_system_springboot_angular.security;

import com.system.Learning_system_springboot_angular.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserServicer userService;
    @Autowired
    public CustomUserDetailsService(UserServicer userService) {
        this.userService = userService;
    }
    @Override
    public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
        UserDTO user = userService.getByCode(userCode);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with code: " + userCode);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUserCode(), // Use userCode as the username
                user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }
}
