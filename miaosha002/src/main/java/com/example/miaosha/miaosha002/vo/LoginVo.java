package com.example.miaosha.miaosha002.vo;

import com.example.miaosha.miaosha002.validator.IsMobile;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class LoginVo {
    @NotNull
    @IsMobile
    private String telphone;

    @NotNull
    private String password;


    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
