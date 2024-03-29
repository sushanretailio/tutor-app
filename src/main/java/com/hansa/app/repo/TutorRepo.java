/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.Tutor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface TutorRepo extends JpaRepository<Tutor, Long>, JpaSpecificationExecutor<Tutor> {
    
    @Query("select t from Tutor t where t.id =:id ")
    Tutor getById(@Param("id") Long id) ;
    
    @Query("select t from Tutor t where t.mobile =:mobile ")
    Tutor getByMobile(@Param("mobile") String mobile) ;
    
    
    @Query("select t from Tutor t ")
    List<Tutor> find() ;
    
    @Modifying
    @Query("update Tutor t set credit= :credit where t.id =:id ")
    void updateCredit(@Param("id") Long id, @Param("credit") int credit) ;
    
   
}
