package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    public void save(CourseDTO course);
    public CourseDTO findById(Integer id);
    public List<CourseDTO> findAll();
    public void update(CourseDTO course);
    public void delete(CourseDTO id);
    public List<CourseDTO> findCoursesByTeacherId(Integer teacherId);
    List<CourseDTO> findCoursesByStudentId(Integer studentId);
}
