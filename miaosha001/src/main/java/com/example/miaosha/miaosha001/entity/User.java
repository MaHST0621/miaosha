package com.example.miaosha.miaosha001.entity;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2021-12-04 14:12:04
 */
public class User implements Serializable {
    private static final long serialVersionUID = 902090976375345019L;
    
    private Long id;
    
    private String userName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}

