package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.MaterialFileDTO;

import java.util.List;

public interface MaterialFileService {
    public void saveMaterialFile(MaterialFileDTO materialFileDTO);
    public void updateMaterialFile(MaterialFileDTO materialFileDTO);
    public MaterialFileDTO getMaterialFileById(Integer id);
    public List<MaterialFileDTO> getAllMaterialFiles();
    public void deleteMaterialFile(Integer id);
    public List<MaterialFileDTO> getFilesByMaterialId(Integer materialId);
    public void addNewFile(MaterialFileDTO dto);
}
