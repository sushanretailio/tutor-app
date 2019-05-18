/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.City;
import com.hansa.app.data.ClassCategory;
import com.hansa.app.data.ClassGroup;
import com.hansa.app.data.State;
import com.hansa.app.data.SubjectMaster;
import com.hansa.app.model.ConfigData;
import com.hansa.app.repo.CategoryRepo;
import com.hansa.app.repo.CityRepo;
import com.hansa.app.repo.ClassGroupRepo;
import com.hansa.app.repo.StateRepo;
import com.hansa.app.repo.SubjectRepo;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author sushant
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/config")
public class ConfigResource {
    
    private static final Log log = LogFactory.getFactory().getInstance(ConfigResource.class);
    
    private static ConfigData configData;
    
    @Autowired
    private ClassGroupRepo classGroupRepo;
    
    @Autowired
    private CategoryRepo categoryRepo;
    
    @Autowired
    private SubjectRepo subjectRepo;
    
    @Autowired
    private StateRepo stateRepo;
    
    @Autowired
    private CityRepo cityRepo;
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/category", method = RequestMethod.POST) 
    public Iterable<ClassCategory> updateClassCatrgory(@RequestBody List<ClassCategory> group) {
        return categoryRepo.saveAll(group);
        
    }
    
    @RequestMapping(value = "/category", method = RequestMethod.GET) 
    public Iterable<ClassCategory> getClassCategory() {
        return categoryRepo.findAll();
        
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/subject", method = RequestMethod.POST) 
    public Iterable<SubjectMaster> updateSubject(@RequestBody List<SubjectMaster> group) {
        return subjectRepo.saveAll(group);
        
    }
    
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/category/{id}/class")
    public void addClass(@PathVariable(name = "id") Long id, @RequestBody ClassGroup cls) {
        cls.setClassGroupId(id);
        classGroupRepo.save(cls);
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/category/{id}/class")
    @Transactional
    public void deleteClass(@PathVariable(name = "id") Long id, @RequestBody ClassGroup cls) {
        classGroupRepo.delete(cls.getId(),id);
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/category/{id}/class")
    @Transactional
    public void updateClass(@PathVariable(name = "id") Long id, @RequestBody ClassGroup cls) {
        cls.setClassGroupId(id);
        classGroupRepo.save(cls);
    }
    
    
    
    @CrossOrigin(origins = "*")
    @PostMapping(value = "/class/{id}/subject")
    public void addSubject(@PathVariable(name = "id") Long id, @RequestBody SubjectMaster cls) {
        cls.setClassGroupId(id);
        subjectRepo.save(cls);
    }
    
    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/class/{id}/subject")
    @Transactional
    public void deleteSubject(@PathVariable(name = "id") Long id, @RequestBody SubjectMaster cls) {
        subjectRepo.delete(cls.getId(),id);
    }
    
    @CrossOrigin(origins = "*")
    @PutMapping(value = "/class/{id}/subject")
    @Transactional
    public void updateSubject(@PathVariable(name = "id") Long id, @RequestBody SubjectMaster cls) {
        cls.setClassGroupId(id);
        subjectRepo.save(cls);
    }
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/class", method = RequestMethod.POST) 
    public Iterable<ClassGroup> updateClass(@RequestBody List<ClassGroup> group) {
        return classGroupRepo.saveAll(group);
        
    }
    
    @RequestMapping(value = "/class", method = RequestMethod.GET) 
    public Iterable<ClassGroup> updateClass(@RequestParam(name = "groupId", required = false) Long groupId) {
        if(groupId!=null) {
            return classGroupRepo.allByGroup(groupId);
        }
        return classGroupRepo.all();
        
    }
    
    
    @RequestMapping(value = "/subject", method = RequestMethod.GET) 
    public Iterable<SubjectMaster> updateSubject(@RequestParam(name = "groupId",required = false) Long groupId) {
        if(groupId!=null) {
            return subjectRepo.allByGroup(groupId);
        }
        return subjectRepo.all();
        
    }
    
    @RequestMapping(value = "/state", method = RequestMethod.GET)
    public Iterable<State> getStates() {
        return stateRepo.findAll();
    }
    
    @RequestMapping(value = "/{id}/city", method = RequestMethod.GET)
    public Iterable<City> getCities(@PathVariable("id") Long id) {
        return cityRepo.getByState(id);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public State addState(@RequestBody State state) {
        return stateRepo.save(state);
    }
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}/city", method = RequestMethod.POST)
    public City addState(@PathVariable("id") Long id,@RequestBody City city) {
        city.setStateId(id);
        return cityRepo.save(city);
    }
    
    
    @RequestMapping(method = RequestMethod.GET)
    public ConfigData getConfig() {
        if (configData == null) {
            log.info("Loading  db");
            ConfigData config = new ConfigData();
            config.setClasses(classGroupRepo.all());
            config.setSubjects(subjectRepo.all());
            config.setCategories(categoryRepo.findAll());
            configData = config;
        } else {
        log.info("Using  cache");
    }
        return configData;
    }
    
}
