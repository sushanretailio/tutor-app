/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.SubjectMaster;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sushant
 */

public interface SubjectRepo extends CrudRepository<SubjectMaster, Long> {
    
    @Query("select cg from SubjectMaster cg")
    public List<SubjectMaster> all();
}
