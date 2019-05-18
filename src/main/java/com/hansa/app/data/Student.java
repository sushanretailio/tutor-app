/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.data;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author sushant
 */

@Entity
@Table(name = "student")
public class Student {

    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String location;
    private String mobile;
    private String email;
    private String category;
    private String classcategory;
    private String particularClass;
    private String subjects;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    private String preferGender ;
    private String preferTiming;
    private String preferDay;
    private String preferFee;
    private String performance;
    private String reasonForQuery;
    private String anythingElse;
    
    
    private String addrLine1;
    private String zipCode;
    private String states;
    private String city;
    private String whatsappNumber;
    private String turorType;
    
    private String sequenceId;
    
    
    @Transient
    private List<Review> reviews;
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getClasscategory() {
        return classcategory;
    }

    public void setClasscategory(String classcategory) {
        this.classcategory = classcategory;
    }

    public String getParticularClass() {
        return particularClass;
    }

    public void setParticularClass(String particularClass) {
        this.particularClass = particularClass;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPreferGender() {
        return preferGender;
    }

    public void setPreferGender(String preferGender) {
        this.preferGender = preferGender;
    }

    public String getPreferTiming() {
        return preferTiming;
    }

    public void setPreferTiming(String preferTiming) {
        this.preferTiming = preferTiming;
    }

    public String getPreferDay() {
        return preferDay;
    }

    public void setPreferDay(String preferDay) {
        this.preferDay = preferDay;
    }

    public String getPreferFee() {
        return preferFee;
    }

    public void setPreferFee(String preferFee) {
        this.preferFee = preferFee;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getReasonForQuery() {
        return reasonForQuery;
    }

    public void setReasonForQuery(String reasonForQuery) {
        this.reasonForQuery = reasonForQuery;
    }

    public String getAnythingElse() {
        return anythingElse;
    }

    public void setAnythingElse(String anythingElse) {
        this.anythingElse = anythingElse;
    }

    public String getAddrLine1() {
        return addrLine1;
    }

    public void setAddrLine1(String addrLine1) {
        this.addrLine1 = addrLine1;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getTurorType() {
        return turorType;
    }

    public void setTurorType(String turorType) {
        this.turorType = turorType;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }
    
    
    
}
