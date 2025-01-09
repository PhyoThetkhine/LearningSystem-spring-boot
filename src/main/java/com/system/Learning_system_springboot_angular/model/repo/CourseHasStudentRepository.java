package com.system.Learning_system_springboot_angular.model.repo;
import com.system.Learning_system_springboot_angular.model.entity.CourseHasStudent;
import com.system.Learning_system_springboot_angular.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseHasStudentRepository extends JpaRepository<CourseHasStudent, CourseHasStudent.CourseHasStudentPK> {
    List<CourseHasStudent> findByCourseIdAndStatus(Integer courseId, Status status);
}
