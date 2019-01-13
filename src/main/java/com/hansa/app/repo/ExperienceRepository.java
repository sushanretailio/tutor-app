package com.hansa.app.repo;

import com.hansa.app.data.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



public interface ExperienceRepository extends CrudRepository<Experience, Long> {

    @Query("select m from Experience m where m.tutorId =:tutorId")
    public List<Experience> get(@Param("tutorId") Long tutorId);
   
    @Query("select m from Experience m where m.id =:id")
    public Experience getById(@Param("id") Long id);
   
}
