/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hansa.app.data.Tutor;
import java.io.File;

/**
 *
 * @author sushant
 */
public class Test {
    
    public static void main(String[] args) throws Exception {
        new ObjectMapper().readValue(new File("newjson.json"), Tutor.class);
    }
    
}
