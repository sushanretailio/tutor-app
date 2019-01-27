/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.City;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */
public interface CityRepo extends JpaRepository<City, Long> {
    
    @Query("SELECT c from City c where c.stateId =:stateId")
    public List<City> getByState(@Param("stateId") Long stateId) ;
}
