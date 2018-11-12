/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.filter;

import com.hansa.app.data.User;
import com.hansa.app.error.UnAuthoriseException;
import com.hansa.app.service.JwtTokenService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author sushant
 */
@Component
public class AppFilter extends OncePerRequestFilter {

    private Log log = LogFactory.getLog(AppFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest hsr, HttpServletResponse hsr1, FilterChain fc) throws ServletException, IOException {
        log.info("Filter "+hsr.getRequestURI());

        /*
        String requestpath = hsr.getRequestURI();
        if (!requestpath.contains("/login")) {
            log.info("Request path " + requestpath);
            String token = hsr.getHeader("Authorization");
            if (token == null) {
                log.info("No Token found");
                hsr1.setStatus(HttpStatus.UNAUTHORIZED.value());
                return ;
                
            } else {
                User user = new JwtTokenService().parse(token);
                log.info("User > " + user.getUserId());
                hsr1.addHeader("userId", user.getUserId());
                hsr1.addHeader("role", user.getType());
            }
        }
*/
        fc.doFilter(hsr, hsr1);

    }

}
