/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.search;

import com.hansa.app.data.Gender;
import com.hansa.app.data.Tutor;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class SearchDB {
    
    @Autowired
    
    private JdbcTemplate template;
    
    public List<Tutor> search(SearchParam param) {
        String q = "select t.id, t.name, t.gender,t.email,t.location,t.mobile,t.qualification from tutor t left join pincode p on p.tutor_id = t.id and p.zip="+param.getZip()+" left join "
                + "class_subject_mapping m on m.tutor_id = t.id and m.class_group_id="+param.getStdClass()+" WHERE ";
        if(param.getStdClass()!=null) {
            q+=" m.class_group_id= "+param.getStdClass();
            if(param.getSubject()!=null) {
                q+="  AND m.subject_master_id="+param.getSubject();
            }
        }
        if(param.getGender()!=null) {
            q+=" AND t.gender='"+param.getGender().name()+"'";
        }
        if(param.getQualification()!=null && !param.getQualification().isEmpty()) {
            q+=" AND t.qualification='"+param.getQualification()+"' limit 100";
        }
        
        
        System.out.println(q);
        return template.query(q, (ResultSet rs) -> {
            List<Tutor> list = new ArrayList<>();
            while(rs.next()) {
                Tutor t = new Tutor();
                t.setId(rs.getLong(1));
                t.setName(rs.getString(2));
                t.setGender(Gender.valueOf(rs.getString(3)));
                t.setEmail(rs.getString(4));t.setLocation(rs.getString(5));
                t.setMobile(rs.getString(6));
                t.setQualification(rs.getString(7));
                list.add(t);
            }
            return list;
        });
    }
    
}
