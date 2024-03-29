/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.data;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author sushant
 */

@Entity
@Table(name = "tutor")
public class Tutor implements Serializable {
    
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
    private String experience;
    private String qualification;
    private String city;
    private String state;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private JobType jobType;
    private String sequenceId;
    private String otp;
    private boolean otpValidated;
    
    
    private String dob ;
    private String whatsappNumber;
    
    @OneToMany(mappedBy = "tutor")
    private List<TutorType> types;
    private String partTimeReason;
    
    @Transient
    private List<ClassSubjectMapping> mapping;
    
    @Transient
    private List<Education> education;
    
    @Transient
    private List<ZIpCode> zipCode;
    
    @Transient
    private List<Experience> experiences;
    
    private int credit;
    private String imageUrl;
    
    @Transient
    private int age;
    
    
    
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

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<ClassSubjectMapping> getMapping() {
        return mapping;
    }

    public void setMapping(List<ClassSubjectMapping> mapping) {
        this.mapping = mapping;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<ZIpCode> getZipCode() {
        return zipCode;
    }

    public void setZipCode(List<ZIpCode> zipCode) {
        this.zipCode = zipCode;
    }

    public List<TutorType> getTypes() {
        return types;
    }

    public void setTypes(List<TutorType> types) {
        this.types = types;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getWhatsappNumber() {
        return whatsappNumber;
    }

    public void setWhatsappNumber(String whatsappNumber) {
        this.whatsappNumber = whatsappNumber;
    }

    public String getPartTimeReason() {
        return partTimeReason;
    }

    public void setPartTimeReason(String partTimeReason) {
        this.partTimeReason = partTimeReason;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<Experience> experiences) {
        this.experiences = experiences;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId) {
        this.sequenceId = sequenceId;
    }

    public boolean isOtpValidated() {
        return otpValidated;
    }

    public void setOtpValidated(boolean otpValidated) {
        this.otpValidated = otpValidated;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
    
}
