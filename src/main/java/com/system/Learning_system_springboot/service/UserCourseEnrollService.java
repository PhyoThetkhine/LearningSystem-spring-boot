package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.UserCourseEnrollDTO;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll;

import java.util.List;

public interface UserCourseEnrollService {
    public List<UserCourseEnrollDTO> findActiveStudentByCourse(Integer courseId);
    public List<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId);
    public List<UserCourseEnrollDTO> findActiveTeacherByCourse(Integer courseId);
    public List<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId);
    public UserCourseEnrollDTO getUserCourseById(UserCourseEnroll.UserCourseEnrollPK id) ;
    public void saveUserCourseStudent(UserCourseEnrollDTO dto) ;
    public void saveUserCourseTeacher(UserCourseEnrollDTO dto) ;
    public void deleteUserCourseEnroll(UserCourseEnroll.UserCourseEnrollPK id);
    public void reEnrollUserCourse(UserCourseEnroll.UserCourseEnrollPK id);
    public List<UserCourseEnrollDTO> getAll() ;
}
