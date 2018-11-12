/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.service;

import com.hansa.app.data.TransactionData;
import com.hansa.app.model.PagedResponse;
import com.hansa.app.repo.TransRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author sushant
 */

@Service
public class TransService {
    
    
    @Autowired
    private TransRepo transRepo;
    
    public void save(TransactionData data) {
        transRepo.save(data);
    }
    
    public void save(List<TransactionData> data) {
        transRepo.saveAll(data);
    }
    
    public PagedResponse get(Long user, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TransactionData> byTutor = transRepo.getByTutor(user, pageRequest);
        PagedResponse resp = new PagedResponse();
        resp.setContents(byTutor.getContent());
        resp.setNext(byTutor.hasNext());
        resp.setTotalSize(byTutor.getTotalElements());
        resp.setPage(byTutor.getTotalPages());
        return resp;
    }
    
    
}
