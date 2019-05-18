/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import java.net.URLEncoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class SMSService {
    private String URL ="https://www.proactivesms.in/sendsms.jsp?user=hansat&password=e4c0e17d87XX&senderid=hansat";
    
    private static final Log log = LogFactory.getFactory().getInstance(SMSService.class);
    
     
    public void sendSMS(String mobile, String message) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        
        log.info("Number "+mobile+", Message "+message);
        try {
            String encodedSms = URLEncoder.encode(message,"utf-8");
            URL = URL+"&mobiles="+mobile +"&sms="+encodedSms;
            log.info("Encoded url "+URL);
            HttpGet getRequest = new HttpGet(URL);
            HttpResponse response = httpClient.execute(getRequest);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);
            log.info("SMS Response : "+apiOutput);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }
}
