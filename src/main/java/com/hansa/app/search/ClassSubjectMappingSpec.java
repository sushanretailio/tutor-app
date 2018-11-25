/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.search;

import com.hansa.app.data.ClassSubjectMapping;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author sushant
 */
public class ClassSubjectMappingSpec implements  Specification<ClassSubjectMapping>{

    private Integer cls;
    private Integer subject;
    
    public ClassSubjectMappingSpec(int cls, int subject) {
        this.cls = cls;
        this.subject = subject;
    }
    
    
    @Override
    public Predicate toPredicate(Root<ClassSubjectMapping> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> all = new ArrayList<>();
        if(cls!=null) {
            all.add(cb.equal(root.get("classGroup").get("id"), cls));
        }
        if(subject!=null) {
            all.add(cb.equal(root.get("subjectMaster").get("id"), subject));
        }
        return andTogether(all, cb);
        
    }
    
    private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
         Predicate[] arr = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(arr));
    }
    
    
}
