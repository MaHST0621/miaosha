package com.example.miaosha.miaosha002.entity;

import java.io.Serializable;

/**
 * (UserPassword)实体类
 *
 * @author makejava
 * @since 2021-12-04 22:31:42
 */
public class UserPassword implements Serializable {
    private static final long serialVersionUID = -66730096417150413L;
    
    private Integer id;
    
    private String encrptPassword;
    
    private Integer userId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEncrptPassword() {
        return encrptPassword;
    }

    public void setEncrptPassword(String encrptPassword) {
        this.encrptPassword = encrptPassword;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

}

