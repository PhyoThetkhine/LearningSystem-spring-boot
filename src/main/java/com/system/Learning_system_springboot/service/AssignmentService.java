package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {
    public void save(AssignmentDTO dto) ;
    public void update(AssignmentDTO dto) ;
    public AssignmentDTO findById(Integer id) ;
    public List<AssignmentDTO> findAll();
    public void delete(Integer id) ;
    public List<AssignmentDTO> findAssignmentsByCourseId(Integer courseId) ;
}
