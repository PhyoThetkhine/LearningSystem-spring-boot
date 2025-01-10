package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.SubmissionFileDTO;

import java.util.List;

public interface SubmissionFileService {
    public void saveSubmissionFile(SubmissionFileDTO dto);
    public List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId);
    public SubmissionFileDTO getFileById(Integer Id);
    public void saveNewFile(SubmissionFileDTO submissionFileDTO);
}
