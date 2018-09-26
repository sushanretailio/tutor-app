/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */
public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    
    @Query(value = "select count(*) from job_application where job_id =:jobId",nativeQuery = true)
    int getApplicationCount(@Param("jobId") Long jobId); 
    
}
