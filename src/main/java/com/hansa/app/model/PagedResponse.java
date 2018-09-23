/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hansa.app.model;

import java.util.List;

/**
 *
 * @author sushant
 */
public class PagedResponse {
    
    private int page;
    private int size;
    private boolean next;
    private List contents;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    
    

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public List getContents() {
        return contents;
    }

    public void setContents(List contents) {
        this.contents = contents;
    }

   
    
    
}
