package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.AssignmentFileDTO;

import java.util.List;

public interface AssignmentFileService {
    public void saveAssignmentFile(AssignmentFileDTO dto);
    public List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId);
    public void addnewFile(AssignmentFileDTO dto) ;

}
