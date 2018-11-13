/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.model;

import com.hansa.app.data.ClassGroup;
import com.hansa.app.data.SubjectMaster;
import java.util.List;

/**
 *
 * @author sushant
 */
public class ConfigData {
    
    private List<ClassGroup> classes;
    private List<SubjectMaster> subjects;

    public List<ClassGroup> getClasses() {
        return classes;
    }

    public void setClasses(List<ClassGroup> classes) {
        this.classes = classes;
    }

    public List<SubjectMaster> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectMaster> subjects) {
        this.subjects = subjects;
    }
    
    
    
}
