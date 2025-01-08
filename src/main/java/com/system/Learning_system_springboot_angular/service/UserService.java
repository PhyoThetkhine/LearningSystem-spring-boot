package com.system.Learning_system_springboot_angular.service;

import com.system.Learning_system_springboot_angular.model.entity.Role;
import com.system.Learning_system_springboot_angular.model.entity.Status;
import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.model.repo.UserRepository;
import com.system.Learning_system_springboot_angular.model.exception.InvalidField;
import com.system.Learning_system_springboot_angular.model.exception.InvalidFieldsException;
import com.system.Learning_system_springboot_angular.model.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.Objects;
import static com.system.Learning_system_springboot_angular.service.PasswordGenerator.generatePassword;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) throws InvalidFieldsException {
        InvalidFieldsException invalidFieldsException = new InvalidFieldsException("Validation failed");
        User activeDuplicate = userRepository.findByEmailAndStatus(user.getEmail(), Status.ACTIVE);
        if (activeDuplicate != null && !Objects.equals(user.getId(), activeDuplicate.getId())) {
            invalidFieldsException.addField(new InvalidField("email", "A user with this email already exists as a " +activeDuplicate.getRole().getDisplayName()));
        } else {
            User terminatedDuplicate = userRepository.findByEmailAndStatus(user.getEmail(), Status.TERMINATE);
            if (terminatedDuplicate != null && !Objects.equals(user.getId(), terminatedDuplicate.getId())) {
                invalidFieldsException.addField(new InvalidField("email", "A user with this email already exists and Terminated as a " + terminatedDuplicate.getRole().getDisplayName()));
            }
        }
        Integer creatorId = user.getCreateAdmin().getId();
        User creator = userRepository.findById(creatorId).orElseThrow(() -> new ServiceException("Creator Not Found"));
        if (!creator.getRole().equals(Role.ADMIN) && !creator.getRole().equals(Role.DEPARTMENT_HEAD)) {
            throw new ServiceException("Not allow to create!");
        }
        if (invalidFieldsException.hasFields()) {
            throw invalidFieldsException;
        }
        user.setCreateAdmin(creator);
        String password = generatePassword();
        user.setPassword(password);
        String userCode = generateUserCode(user.getRole());
        user.setUserCode(userCode);
        return userRepository.save(user);
    }

    private String generateUserCode(Role role) {
        String prefix = switch (role) {
            case ADMIN -> "ADM";
            case TEACHER -> "TEA";
            case STUDENT -> "STU";
            default -> "USR";
        };
        Long count = userRepository.countByRole(role);
        return prefix + String.format("%04d", count + 1);
    }

    public User getById(Integer Id){
        return  userRepository.findById(Id).orElseThrow(() -> new ServiceException("User not Found"));
    }


}