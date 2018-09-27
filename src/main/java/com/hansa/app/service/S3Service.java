/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.ByteArrayInputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class S3Service {
    
    final String accessKeyid = "AKIAIT4M6OCMFEXRWTRA";
    final String secretKey = "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";
    final String bucket = "profile.tutor";
    final String baseUrl = "https://s3.ap-south-1.amazonaws.com/profile.tutor";
    
    
    public String save(byte[] data, String type,String key) {
        BasicAWSCredentials credentials = new BasicAWSCredentials(accessKeyid, secretKey);
        AmazonS3 s3 =   AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion("ap-south-1").withPathStyleAccessEnabled(true)
            .build();
            ByteArrayInputStream stream = new ByteArrayInputStream(data);
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentLength(data.length);
            meta.setContentType(type);
            s3.putObject(new PutObjectRequest(bucket, key+"."+type, stream, meta));
            return get(key,type);
    }
    
    public String get(String key,String type) {
        return baseUrl+"/"+key+"."+type;
    }
    
}
