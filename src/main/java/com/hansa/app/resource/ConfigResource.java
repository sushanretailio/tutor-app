/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.hansa.app.data.City;
import com.hansa.app.data.ClassGroup;
import com.hansa.app.data.State;
import com.hansa.app.data.SubjectMaster;
import com.hansa.app.model.ConfigData;
import com.hansa.app.repo.CityRepo;
import com.hansa.app.repo.ClassGroupRepo;
import com.hansa.app.repo.StateRepo;
import com.hansa.app.repo.SubjectRepo;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    private SubjectRepo subjectRepo;
    
    @Autowired
    private StateRepo stateRepo;
    
    @Autowired
    private CityRepo cityRepo;
    
    
    
    @RequestMapping(value = "/class", method = RequestMethod.POST) 
    public Iterable<ClassGroup> updateClass(@RequestBody List<ClassGroup> group) {
        return classGroupRepo.saveAll(group);
        
    }
    
    
    @RequestMapping(value = "/subject", method = RequestMethod.POST) 
    public Iterable<SubjectMaster> updateSubject(@RequestBody List<SubjectMaster> group) {
        return subjectRepo.saveAll(group);
        
    }
    
    
    
    @RequestMapping(value = "/class", method = RequestMethod.GET) 
    public Iterable<ClassGroup> updateClass() {
        return classGroupRepo.all();
        
    }
    
    
    @RequestMapping(value = "/subject", method = RequestMethod.GET) 
    public Iterable<SubjectMaster> updateSubject() {
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
    
    
    @RequestMapping(value = "/state", method = RequestMethod.POST)
    public State addState(@RequestBody State state) {
        return stateRepo.save(state);
    }
    
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
            configData = config;
        } else {
        log.info("Using  cache");
    }
        return configData;
    }
    
}
