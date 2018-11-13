/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author sushant
 */

@Component
@Order(0)
public class AppInterceptor extends HandlerInterceptorAdapter {
    
    private Log log = LogFactory.getLog(AppInterceptor.class);
    
    @Override
    public boolean preHandle(HttpServletRequest request ,HttpServletResponse response ,  Object handler) {
        log.info("Interceptor ");
        return false;
    }
    
    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, @Nullable ModelAndView mav) throws Exception {
        log.info("POST Interceptor ");
    }
    
}
