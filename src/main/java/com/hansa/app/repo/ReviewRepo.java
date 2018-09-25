/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.Review;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface ReviewRepo extends CrudRepository<Review, Long> {
    
    @Query("select a from Review a where a.tutor.id =:tutorId ")
    List<Review> getByTutor(@Param("tutorId") Long tutorId) ;
    
    
    @Query("select a from Review a where a.student.id =:studentId ")
    List<Review> getByStudent(@Param("studentId") Long studentId) ;
    
}
