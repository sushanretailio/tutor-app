/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.search;

import com.hansa.app.data.ClassSubjectMapping;
import com.hansa.app.data.Tutor;
import com.hansa.app.data.ZIpCode;
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
public class SearchSpecification implements Specification<Tutor> {
    
    private SearchParam params;
    public SearchSpecification(SearchParam param) {
        this.params = param;
    }
    

    @Override
    public Predicate toPredicate(Root<Tutor> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        List<Predicate> all = new ArrayList<>();
        
        if(params.getGender()!=null) {
            all.add(cb.equal(root.get("gender"), params.getGender()));
        }
        
        if(params.getZip()!=null) {
            Root<ZIpCode> zip = cq.from(ZIpCode.class);
            zip.joinSet("tutorId", JoinType.LEFT);
            all.add(cb.equal(zip.get("zip"), params.getZip()));
            
        }
        //ClassSubjectMappingSpec spec = new ClassSubjectMappingSpec(params.getStdClass(), params.getSubject());
        
        return andTogether(all, cb);
    }
    
     private Predicate andTogether(List<Predicate> predicates, CriteriaBuilder cb) {
         Predicate[] arr = new Predicate[predicates.size()];
        return cb.and(predicates.toArray(arr));
    }
    
}
