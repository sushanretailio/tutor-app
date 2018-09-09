/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app;


import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sushant
 */

public interface StudentRepo extends CrudRepository<Student, Long> {
    
    
}
