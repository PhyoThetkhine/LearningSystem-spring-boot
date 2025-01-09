package com.system.Learning_system_springboot_angular.model.repo;

import com.system.Learning_system_springboot_angular.model.entity.Role;
import com.system.Learning_system_springboot_angular.model.entity.Status;
import com.system.Learning_system_springboot_angular.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Long countByRole(Role role);
    List<User> findByRoleAndStatus(Role role, Status status);
    List<User> findByStatus(Status status);
    User findByEmail(String email);
    Optional<User> findByIdAndStatus(Integer id, Status status);
    User findByEmailAndStatus(String email,Status status);
    Optional<User> findByUserCode(String code);
}