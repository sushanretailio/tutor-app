/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.Education;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface EducationRepo extends CrudRepository<Education, Long> {
    
   
   @Query("select m from Education m where m.tutorId =:tutorId")
   public List<Education> get(@Param("tutorId") Long tutorId);
   
   @Query("select m from Education m where m.id =:id")
   public Education getById(@Param("id") Long id);
   
    
}
