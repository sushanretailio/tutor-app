/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sushant
 */
public interface UserRepo extends JpaRepository<User, Long> {
    
    @Query("select u from User u where u.userId =:userId AND u.password =:password")
    User get(@Param("userId") String userId,@Param("password") String password);
    
    
    
}
