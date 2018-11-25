/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.ZIpCode;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sushant
 */
@Repository
public interface ZipRepo extends CrudRepository<ZIpCode, Long>  {
    
    @Query("select z from ZIpCode z where z.tutorId =:tutorId")
    public List<ZIpCode> getByTutorId(@Param("tutorId") Long tutorId);
}
