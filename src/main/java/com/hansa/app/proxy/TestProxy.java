/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.proxy;

import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 * @author sushant
 */

@FeignClient(value = "http://super-user-service")
public interface TestProxy {
    
}
