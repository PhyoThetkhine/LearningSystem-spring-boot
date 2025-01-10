package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.UserCourseEnrollDTO;
import com.system.Learning_system_springboot.model.entity.EnrollPosition;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll.UserCourseEnrollPK;
import com.system.Learning_system_springboot.model.exception.ServiceException;

import com.system.Learning_system_springboot.model.repo.UserCourseEnrollRepository;
import com.system.Learning_system_springboot.service.UserCourseEnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCourseEnrollServiceImpl implements UserCourseEnrollService {

    @Autowired
    private UserCourseEnrollRepository userCourseEnrollRepository;

    @Override
    public List<UserCourseEnrollDTO> findActiveStudentByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.STUDENT_ENROLL, Status.ACTIVE
        ).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.STUDENT_ENROLL, Status.DROP
        ).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserCourseEnrollDTO> findActiveTeacherByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.TEACHER_ENROLL, Status.ACTIVE
        ).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.TEACHER_ENROLL, Status.DROP
        ).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public UserCourseEnrollDTO getUserCourseById(UserCourseEnrollPK id) throws ServiceException {
        UserCourseEnroll userCourseEnroll = userCourseEnrollRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UserCourseEnroll not found with id: " + id));
        return convertToDTO(userCourseEnroll);
    }

    @Override
    public void saveUserCourseStudent(UserCourseEnrollDTO dto) {
        UserCourseEnroll userCourseEnroll = convertToEntity(dto);
        userCourseEnroll.setEnrollPosition(EnrollPosition.STUDENT_ENROLL);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public void saveUserCourseTeacher(UserCourseEnrollDTO dto) {
        UserCourseEnroll userCourseEnroll = convertToEntity(dto);
        userCourseEnroll.setEnrollPosition(EnrollPosition.TEACHER_ENROLL);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public void deleteUserCourseEnroll(UserCourseEnrollPK id) throws ServiceException {
        if (!userCourseEnrollRepository.existsById(id)) {
            throw new ServiceException("UserCourseEnroll not found with id: " + id);
        }
        userCourseEnrollRepository.deleteById(id);
    }

    @Override
    public void reEnrollUserCourse(UserCourseEnrollPK id) throws ServiceException {
        UserCourseEnroll userCourseEnroll = userCourseEnrollRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UserCourseEnroll not found with id: " + id));
        userCourseEnroll.setStatus(Status.ACTIVE);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public List<UserCourseEnrollDTO> getAll() {
        return userCourseEnrollRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert Entity to DTO
    private UserCourseEnrollDTO convertToDTO(UserCourseEnroll userCourseEnroll) {
        UserCourseEnrollDTO dto = new UserCourseEnrollDTO();
        dto.setCourseId(userCourseEnroll.getCourse().getId());
        dto.setCourse(userCourseEnroll.getCourse());
        dto.setUserId(userCourseEnroll.getUser().getId());
        dto.setUser(userCourseEnroll.getUser());
        dto.setCreateAdminId(userCourseEnroll.getCreateAdmin().getId());
        dto.setCreateAdmin(userCourseEnroll.getCreateAdmin());
        dto.setCreateAdminName(userCourseEnroll.getCreateAdmin().getName());
        dto.setStatus(userCourseEnroll.getStatus().name());
        dto.setCreateDate(userCourseEnroll.getCreateDate());
        dto.setUpdateDate(userCourseEnroll.getUpdateDate());
        dto.setEnrollPosition(userCourseEnroll.getEnrollPosition());
        return dto;
    }

    // Helper method to convert DTO to Entity
    private UserCourseEnroll convertToEntity(UserCourseEnrollDTO dto) {
        UserCourseEnroll userCourseEnroll = new UserCourseEnroll();
        UserCourseEnrollPK id = new UserCourseEnrollPK();
        id.setCourseId(dto.getCourseId());
        id.setUserId(dto.getUserId());
        userCourseEnroll.setId(id);
        userCourseEnroll.setCourse(dto.getCourse());
        userCourseEnroll.setUser(dto.getUser());
        userCourseEnroll.setCreateAdmin(dto.getCreateAdmin());
        userCourseEnroll.setStatus(Status.valueOf(dto.getStatus()));
        userCourseEnroll.setCreateDate(dto.getCreateDate());
        userCourseEnroll.setUpdateDate(dto.getUpdateDate());
        userCourseEnroll.setEnrollPosition(dto.getEnrollPosition());
        return userCourseEnroll;
    }
}