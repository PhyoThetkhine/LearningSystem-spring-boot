package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.UserDTO;
import com.system.Learning_system_springboot.model.entity.Role;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.User;
import com.system.Learning_system_springboot.model.exception.InvalidFieldsException;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.exception.UserNotFoundException;
import com.system.Learning_system_springboot.model.repo.UserRepository;
import com.system.Learning_system_springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO saveUser(UserDTO dto) throws InvalidFieldsException {
        User activeDuplicate = userRepository.findByEmailAndStatus(dto.getEmail(), Status.ACTIVE);
        if (activeDuplicate != null && !Objects.equals(dto.getId(), activeDuplicate.getId())) {
            throw new InvalidFieldsException("A user with this email already exists as a " + activeDuplicate.getRole().getDisplayName());
        } else {
            User terminatedDuplicate = userRepository.findByEmailAndStatus(dto.getEmail(), Status.TERMINATE);
            if (terminatedDuplicate != null && !Objects.equals(dto.getId(), terminatedDuplicate.getId())) {
                throw new InvalidFieldsException("A user with this email already exists and Terminated as a " + terminatedDuplicate.getRole().getDisplayName());
            }
        }
        User creator = userRepository.findById(dto.getCreateAdmin().getId()).orElseThrow(() -> new UserNotFoundException("Creator Not Found"));
        if (!creator.getRole().equals(Role.ADMIN) && !creator.getRole().equals(Role.DEPARTMENT_HEAD)) {
            throw new ServiceException("Permission denied!");
        }
        dto.setCreateAdmin(creator);
        dto.setPassword(passwordEncoder.encode("yan_kee"));
        dto.setUserCode(generateUserCode(dto.getRole()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        User user = modelMapper.map(dto, User.class);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public String generateUserCode(Role role) {
        String prefix = switch (role) {
            case ADMIN -> "ADM";
            case TEACHER -> "TEA";
            case STUDENT -> "STU";
            case DEPARTMENT_HEAD -> "DPH";
        };
        Long count = userRepository.countByRole(role);
        return prefix + String.format("%04d", count + 1);
    }

    @Override
    public UserDTO getByCode(String code) {
        User user = userRepository.findByUserCode(code)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public UserDTO getById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not Found"));
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public Page<UserDTO> getAllUser(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);
        return users.map(user -> modelMapper.map(user, UserDTO.class));
    }

    @Override
    public void terminateUserById(Integer id) {
        User deleteUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        deleteUser.setStatus(Status.TERMINATE);
        userRepository.save(deleteUser);
    }

    @Override
    public void activeUserById(Integer id) {
        User activeUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        activeUser.setStatus(Status.ACTIVE);
        userRepository.save(activeUser);
    }

    @Override
    public Page<UserDTO> findStudentsNotEnrolledInCourse(Integer courseId, Pageable pageable) {
        Page<User> students = userRepository.findStudentsNotEnrolledInCourse(courseId, pageable);
        return students.map(student -> modelMapper.map(student, UserDTO.class));
    }

    @Override
    public Page<UserDTO> findTeachersNotAssignedToCourse(Integer courseId, Pageable pageable) {
        Page<User> teachers = userRepository.findTeachersNotAssignedToCourse(courseId, pageable);
        return teachers.map(teacher -> modelMapper.map(teacher, UserDTO.class));
    }

    @Override
    public void changePassword(String userCode, String currentPassword, String newPassword) {
        User user = userRepository.findByUserCode(userCode)
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new InvalidFieldsException("Current password is incorrect");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Override
    public UserDTO updateByUser(UserDTO dto) {
        User existingUser = userRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(dto, existingUser);
        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDTO.class);
    }
}