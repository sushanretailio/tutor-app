/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.TransactionData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface TransRepo extends CrudRepository<TransactionData, Long> {
    
   @Query("select t from TransactionData t where t.user = :userId") 
   public Page<TransactionData> getByTutor(@Param("userId") Long userId, Pageable page);
    
}
