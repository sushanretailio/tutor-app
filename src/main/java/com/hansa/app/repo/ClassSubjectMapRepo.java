/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.ClassSubjectMapping;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface ClassSubjectMapRepo extends CrudRepository<ClassSubjectMapping, Long> {
    
   @Modifying 
   @Query("delete from ClassSubjectMapping where tutorId =:tutorId") 
   public void delete(@Param("tutorId") Long tutorId);
   
   @Query("select m from ClassSubjectMapping m where m.tutorId =:tutorId")
   public List<ClassSubjectMapping> get(@Param("tutorId") Long tutorId);
    
}
