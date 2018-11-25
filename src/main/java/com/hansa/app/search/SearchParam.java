/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.search;

import com.hansa.app.data.Gender;

/**
 *
 * @author sushant
 */
public class SearchParam {
    
    private Integer stdClass;
    private String zip;
    private Integer subject;
    private Integer qualification;
    private Gender gender;

    public Integer getStdClass() {
        return stdClass;
    }

    public void setStdClass(Integer stdClass) {
        this.stdClass = stdClass;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    
    
}
