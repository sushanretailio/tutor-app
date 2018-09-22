/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.Tutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface TutorRepo extends CrudRepository<Tutor, Long> {
    
    @Query("select t from Tutor t where t.id =:id ")
    Tutor getById(@Param("id") Long id) ;
}
