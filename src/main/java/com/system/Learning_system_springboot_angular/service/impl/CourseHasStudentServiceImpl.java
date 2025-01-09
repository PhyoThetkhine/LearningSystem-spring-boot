package com.system.Learning_system_springboot_angular.service.impl;

import com.system.Learning_system_springboot_angular.model.dto.CourseHasStudentDTO;
import com.system.Learning_system_springboot_angular.model.entity.CourseHasStudent;
import com.system.Learning_system_springboot_angular.model.entity.Status;
import com.system.Learning_system_springboot_angular.model.entity.User;
import com.system.Learning_system_springboot_angular.model.exception.ServiceException;
import com.system.Learning_system_springboot_angular.model.exception.UserNotFoundException;
import com.system.Learning_system_springboot_angular.model.repo.CourseHasStudentRepository;
import com.system.Learning_system_springboot_angular.model.repo.CourseRepository;
import com.system.Learning_system_springboot_angular.model.repo.UserRepository;
import com.system.Learning_system_springboot_angular.service.CourseHasStudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseHasStudentServiceImpl implements CourseHasStudentService {

    @Autowired
    private CourseHasStudentRepository courseHasStudentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<CourseHasStudentDTO> findActiveStudentByCourse(Integer courseId) {
        List<CourseHasStudent> activeStudents = courseHasStudentRepository.findByCourseIdAndStatus(courseId, Status.ACTIVE);
        return activeStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseHasStudentDTO> findDropStudentByCourse(Integer courseId) {
        List<CourseHasStudent> dropStudents = courseHasStudentRepository.findByCourseIdAndStatus(courseId, Status.DROP);
        return dropStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseHasStudentDTO getCourseHasStudentById(CourseHasStudent.CourseHasStudentPK id) {
        CourseHasStudent courseHasStudent = courseHasStudentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("CourseHasStudent not found with id: " + id));
        return convertToDTO(courseHasStudent);
    }

    @Override
    public void saveCourseHasStudent(CourseHasStudentDTO dto) {
        // Fetch the course and student entities
        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new ServiceException("Course not found with id: " + dto.getCourseId()));
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new UserNotFoundException("Student not found with id: " + dto.getStudentId()));
        User createAdmin = userRepository.findById(dto.getCreateAdminId())
                .orElseThrow(() -> new UserNotFoundException("Create admin not found with id: " + dto.getCreateAdminId()));

        // Create the composite key
        CourseHasStudent.CourseHasStudentPK id = new CourseHasStudent.CourseHasStudentPK();
        id.setCourseId(dto.getCourseId());
        id.setStudentId(dto.getStudentId());

        // Map DTO to entity
        CourseHasStudent courseHasStudent = modelMapper.map(dto, CourseHasStudent.class);
        courseHasStudent.setId(id);
        courseHasStudent.setCourse(course);
        courseHasStudent.setStudent(student);
        courseHasStudent.setCreateAdmin(createAdmin);

        // Save the entity
        courseHasStudentRepository.save(courseHasStudent);
    }

    @Override
    public void deleteCourseHasStudent(CourseHasStudent.CourseHasStudentPK id) {
        CourseHasStudent courseHasStudent = courseHasStudentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("CourseHasStudent not found with id: " + id));
        courseHasStudentRepository.delete(courseHasStudent);
    }

    @Override
    public void reEnrollCourseHasStudent(CourseHasStudent.CourseHasStudentPK id) {
        CourseHasStudent courseHasStudent = courseHasStudentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("CourseHasStudent not found with id: " + id));
        courseHasStudent.setStatus(Status.ACTIVE); // Re-enroll by setting status to ACTIVE
        courseHasStudentRepository.save(courseHasStudent);
    }

    @Override
    public List<CourseHasStudentDTO> getAll() {
        List<CourseHasStudent> courseHasStudents = courseHasStudentRepository.findAll();
        return courseHasStudents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert entity to DTO
    private CourseHasStudentDTO convertToDTO(CourseHasStudent courseHasStudent) {
        CourseHasStudentDTO dto = modelMapper.map(courseHasStudent, CourseHasStudentDTO.class);
        dto.setCourseId(courseHasStudent.getCourse().getId());
        dto.setStudentId(courseHasStudent.getStudent().getId());
        dto.setCreateAdminId(courseHasStudent.getCreateAdmin().getId());
        dto.setCreateAdminName(courseHasStudent.getCreateAdmin().getName());
        return dto;
    }
}