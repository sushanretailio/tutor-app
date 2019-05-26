/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.repo;

import com.hansa.app.data.Faq;
import com.hansa.app.data.FaqCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sushant
 */
@Repository
public interface FaqRepo extends JpaRepository<Faq, Long> {
    
    @Query("select f from Faq f where f.category =:category")
    public List<Faq> getByCategory(@Param("category") FaqCategory category);
}
