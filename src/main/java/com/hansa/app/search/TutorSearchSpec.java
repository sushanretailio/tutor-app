/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.search;

import com.hansa.app.data.ClassSubjectMapping;
import com.hansa.app.data.Gender;
import com.hansa.app.data.Tutor;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

/**
 *
 * @author sushant
 */

public class TutorSearchSpec { 
    /*
    implements Specification<Tutor> 
    private SearchParam params;
     public Predicate toPredicate(Root<Tutor> root ,CriteriaQuery<*> query, CriteriaBuilder cb) {

         Join<ClassSubjectMapping, Tutor> joinInvoice = root.join("rectificationItems", JoinType.LEFT);

         List<Predicate> predicates = new ArrayList<>();
        if (params.getGender() != null) predicates.add(cb.equal(root.get<Gender>("gender"), params.getGender()));

        List<Predicate> innerPredicates = new ArrayList<>();
        if (params.getQualification() != null) predicates.add(cb.equal(joinInvoice.get<String>("ucode"), params.getQualification()));
        if (params.getStdClass() != null ) predicates.add(cb.equal(joinInvoice.get<Integer>("invoiceNo"), params.getStdClass()));

       
        return andTogether(predicates, cb);
    }

    private Predicate andTogether(List<Predicate> predicates , CriteriaBuilder cb) {
        return cb.and(predicates.toTypedArray());
    }

    private Predicate orTogether(List<Predicate> predicates,CriteriaBuilder cb) {
        return cb.or(predicates.toTypedArray())
    }
    */
}
