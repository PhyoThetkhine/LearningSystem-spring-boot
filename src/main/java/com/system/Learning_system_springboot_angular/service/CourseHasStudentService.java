package com.system.Learning_system_springboot_angular.service;

import com.system.Learning_system_springboot_angular.model.dto.CourseHasStudentDTO;
import com.system.Learning_system_springboot_angular.model.entity.CourseHasStudent;

import java.util.List;

public interface CourseHasStudentService {
    public List<CourseHasStudentDTO> findActiveStudentByCourse(Integer courseId);
    public List<CourseHasStudentDTO> findDropStudentByCourse(Integer courseId);
    public CourseHasStudentDTO getCourseHasStudentById(CourseHasStudent.CourseHasStudentPK id) ;
    public void saveCourseHasStudent(CourseHasStudentDTO dto) ;
    public void deleteCourseHasStudent(CourseHasStudent.CourseHasStudentPK id);
    public void reEnrollCourseHasStudent(CourseHasStudent.CourseHasStudentPK id);
    public List<CourseHasStudentDTO> getAll() ;
}
