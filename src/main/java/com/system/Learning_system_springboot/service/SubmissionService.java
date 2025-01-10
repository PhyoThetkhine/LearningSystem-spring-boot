package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.SubmissionDTO;

import java.util.List;

public interface SubmissionService {
    public void save(SubmissionDTO dto);
    public void update(SubmissionDTO dto);
    public SubmissionDTO findById(Integer id);
    public List<SubmissionDTO> findAll();
    public void delete(Integer id);
    public List<SubmissionDTO> findSubmissionsByAssignmentId(Integer assignmentId);
    List<SubmissionDTO> getSubmissionByAssignmentIdAndStudentId(Integer assignmentId,Integer studentId);
}
