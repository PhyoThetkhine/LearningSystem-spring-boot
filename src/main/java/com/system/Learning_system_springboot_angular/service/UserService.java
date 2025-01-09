package com.system.Learning_system_springboot_angular.service;

import com.system.Learning_system_springboot_angular.model.entity.Role;
import com.system.Learning_system_springboot_angular.model.entity.Status;
import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.model.exception.UserNotFoundException;
import com.system.Learning_system_springboot_angular.model.repo.UserRepository;
import com.system.Learning_system_springboot_angular.model.exception.InvalidFieldsException;
import com.system.Learning_system_springboot_angular.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import static com.system.Learning_system_springboot_angular.service.PasswordGenerator.generatePassword;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) throws InvalidFieldsException {

        User activeDuplicate = userRepository.findByEmailAndStatus(user.getEmail(), Status.ACTIVE);
        if (activeDuplicate != null && !Objects.equals(user.getId(), activeDuplicate.getId())) {
            throw new InvalidFieldsException("A user with this email already exists as a "+activeDuplicate.getRole().getDisplayName());

        } else {
            User terminatedDuplicate = userRepository.findByEmailAndStatus(user.getEmail(), Status.TERMINATE);
            if (terminatedDuplicate != null && !Objects.equals(user.getId(), terminatedDuplicate.getId())) {
                throw new InvalidFieldsException("A user with this email already exists and Terminated as a " + terminatedDuplicate.getRole().getDisplayName());
            }
        }
        User creator = userRepository.findById(user.getCreateAdmin().getId()).orElseThrow(() -> new UserNotFoundException("Creator Not Found"));
        if (!creator.getRole().equals(Role.ADMIN) && !creator.getRole().equals(Role.DEPARTMENT_HEAD)) {
            throw new ServiceException("Permission denied!");
        }
        user.setCreateAdmin(creator);

       user.setPassword(passwordEncoder.encode("yan_kee"));

        user.setUserCode(generateUserCode(user.getRole()));
        return userRepository.save(user);
    }


    private String generateUserCode(Role role) {
        String prefix = switch (role) {
            case ADMIN -> "ADM";
            case TEACHER -> "TEA";
            case STUDENT -> "STU";
            case DEPARTMENT_HEAD -> "DPH";
        };
        Long count = userRepository.countByRole(role);
        return prefix + String.format("%04d", count + 1);
    }
    public User getByCode(String code){
        return userRepository.findByUserCode(code).orElseThrow(() -> new UserNotFoundException("User Not Found"));
    }

    public User getById(Integer Id){
        return  userRepository.findById(Id).orElseThrow(() -> new UserNotFoundException("User not Found"));
    }


}