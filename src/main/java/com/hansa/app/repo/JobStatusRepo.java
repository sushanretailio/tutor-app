/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.JobStatusData;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sushant
 */
public interface JobStatusRepo extends CrudRepository<JobStatusData, Long> {
    
}
