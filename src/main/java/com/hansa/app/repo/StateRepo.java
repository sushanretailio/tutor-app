/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.State;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author sushant
 */
public interface StateRepo extends JpaRepository<State, Long> {
    
}
