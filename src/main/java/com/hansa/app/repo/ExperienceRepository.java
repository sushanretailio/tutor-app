package com.hansa.app.repo;

import com.hansa.app.data.Education;
import com.hansa.app.data.Experience;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ExperienceRepository extends CrudRepository<Experience, Long> {

    @Query("select m from Experience m where m.tutorId =:tutorId")
   public List<Experience> get(@Param("tutorId") Long tutorId);
   
}
