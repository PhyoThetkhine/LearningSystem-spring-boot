package com.system.Learning_system_springboot_angular.model.repo;
import com.system.Learning_system_springboot_angular.model.entity.SubmissionFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubmissionFileRepository extends JpaRepository<SubmissionFile , Integer> {
}
