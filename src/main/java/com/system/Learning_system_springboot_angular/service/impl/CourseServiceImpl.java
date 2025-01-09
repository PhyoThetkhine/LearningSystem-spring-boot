package com.system.Learning_system_springboot_angular.service.impl;

import com.system.Learning_system_springboot_angular.model.dto.CourseDTO;
import com.system.Learning_system_springboot_angular.model.entity.Course;
import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.model.exception.CourseNotFound;
import com.system.Learning_system_springboot_angular.model.repo.CourseRepository;
import com.system.Learning_system_springboot_angular.model.repo.UserRepository;
import com.system.Learning_system_springboot_angular.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void save(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        User createAdmin = userRepository.findById(courseDTO.getCreateAdminId())
                .orElseThrow(() -> new RuntimeException("Create admin not found"));
        course.setCreateAdmin(createAdmin);
        courseRepository.save(course);
    }
    @Override
    public CourseDTO findById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound("Course not found with id: " + id));
        return modelMapper.map(course, CourseDTO.class);
    }
    @Override
    public List<CourseDTO> findAll() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public void update(CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new CourseNotFound("Course not found with id: " + courseDTO.getId()));
        modelMapper.map(courseDTO, existingCourse);
        if (courseDTO.getCreateAdminId() != null) {
            User createAdmin = userRepository.findById(courseDTO.getCreateAdminId())
                    .orElseThrow(() -> new RuntimeException("Create admin not found"));
            existingCourse.setCreateAdmin(createAdmin);
        }
        courseRepository.save(existingCourse);
    }
    @Override
    public void delete(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        courseRepository.delete(course);
    }
    @Override
    public List<CourseDTO> findCoursesByTeacherId(Integer teacherId) {
        List<Course> courses = courseRepository.findByCreateAdminId(teacherId);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public List<CourseDTO> findCoursesByStudentId(Integer studentId) {
        // Assuming you have a method in the repository to find courses by student ID
        List<Course> courses = courseRepository.findCoursesByStudentId(studentId);
        return courses.stream()
                .map(course -> modelMapper.map(course, CourseDTO.class))
                .collect(Collectors.toList());
    }
}