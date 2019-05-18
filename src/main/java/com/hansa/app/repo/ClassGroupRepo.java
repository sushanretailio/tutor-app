/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;


import com.hansa.app.data.ClassGroup;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */

public interface ClassGroupRepo extends CrudRepository<ClassGroup, Long> {
    
    @Query("select cg from ClassGroup cg")
    public List<ClassGroup> all();
    
    @Modifying
    @Query("delete from ClassGroup cg where cg.id =:id and cg.classGroupId =:classGroupId ")
    public void delete(@Param("id") Long id, @Param("classGroupId") Long classGroupId);
    
    @Query("select cg from ClassGroup cg where cg.classGroupId =:classGroupId")
    public List<ClassGroup> allByGroup(@Param("classGroupId") Long classGroupId);
}
