/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hansa.app.data.User;
import com.hansa.app.data.UserRole;
import java.util.Date;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class JwtTokenService {
    
    
    private static final Log log = LogFactory.getFactory().getInstance(JwtTokenService.class);
    
    private final String key = "hansaa@2018";
    public String getToken(User user) {
        Date expiry = new Date(System.currentTimeMillis() + 14 * 24 * 60 * 60 * 1000);
        
        return JWT.create()
                .withIssuer("hansaa")
                .withExpiresAt(expiry)
                .withAudience("hansaa")
                .withClaim("userId", user.getId())
                .withClaim("name", user.getUserId())
                .withClaim("role", user.getType())
                .sign(Algorithm.HMAC512(key));
    }
    
    
     public User parse(String token) {
         try {
             JWTVerifier verifier = JWT.require(Algorithm.HMAC512(key))
            .withIssuer("hansaa")
            .build();
         
        DecodedJWT verify = verifier.verify(token);
        
        
        Map map = verify.getClaims();
        String userId = verify.getClaim("name").asString();
        UserRole role = UserRole.valueOf(verify.getClaim("role").asString());
        log.info("user "+userId+", Role "+role);
        User user = new User();
        user.setUserId(userId);
        user.setType(role.name());
        return user;
         } catch(Exception e) {
             log.error(e);
             return null;
         }
    }
    
}
