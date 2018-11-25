/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.filter;

import com.hansa.app.data.User;
import com.hansa.app.service.JwtTokenService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author sushant
 */
@Component
public class AppFilter extends OncePerRequestFilter {

    private Log log = LogFactory.getLog(AppFilter.class);
    
    @Autowired
    private JwtTokenService jwtTokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException, IOException {
        log.info("Filter "+hsr.getRequestURI());
        
        String requestpath = hsr.getRequestURI();
        if (!requestpath.contains("/login")) {
            log.info("Request path " + requestpath);
            String token = hsr.getHeader("Authorization");
            if (token == null) {
                log.info("No Token found");
               // hsr1.setStatus(HttpStatus.UNAUTHORIZED.value());
               // hsr1.sendError(HttpStatus.UNAUTHORIZED.value(),"Token not found or invalid");
            } else {
                User user = jwtTokenService.parse(token);
                if(user!=null) {
                    log.info("User > " + user.getUserId());
                    hsr1.addHeader("userId", user.getUserId());
                    hsr1.addHeader("role", user.getType());
                } else {
                    log.info("No user found for given token ");
                } 
                
            }
        }

        fc.doFilter(hsr, hsr1);

    }

}
