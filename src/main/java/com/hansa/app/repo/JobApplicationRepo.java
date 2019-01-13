/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.JobApplication;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    @Query("select a from JobApplication a where a.job.id =:jobId")
    List<JobApplication> getByJobId(@Param("jobId") Long jobId);
    
    @Query("select a from JobApplication a where a.job.id =:jobId and a.tutor.id =:tutorId")
    JobApplication getByTutorId(@Param("jobId") Long jobId, @Param("tutorId") Long tutorId);
    
    @Query("select a from JobApplication a where a.tutor.id =:tutorId")
    Page<JobApplication> getAllByTutorId(@Param("tutorId") Long tutorId,Pageable page);
    
    @Query("select a from JobApplication a where a.id =:id")
    JobApplication getById(@Param("id") Long id);
    
    
}
