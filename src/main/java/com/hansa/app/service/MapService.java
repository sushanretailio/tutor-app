/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.ClassSubjectMapping;
import com.hansa.app.repo.ClassSubjectMapRepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class MapService {
    
    @Autowired
    private ClassSubjectMapRepo  classSubjectMapRepo;
    
    @Transactional
    public void update(Long id, List<ClassSubjectMapping> map) {
        classSubjectMapRepo.delete(id);
        //map.forEach(it-> it.setTutorId(id));
        classSubjectMapRepo.saveAll(map);
    }
}
