package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {
    public void save(MaterialDTO material);
    public  void update(MaterialDTO material);
    public  MaterialDTO findById(Integer id);
    public List<MaterialDTO> findAll();
    public void delete(Integer id);
    public List<MaterialDTO> findMaterialsByCourseId(Integer courseId);
}
