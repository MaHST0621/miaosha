package com.example.miaosha.miaosha002.vo;

import com.example.miaosha.miaosha002.validator.IsMobile;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ToString
public class RegisterVo {
    /**
     * 用户姓名
     */
    @NotNull
    private String name;
    /**
     * 1:男性 2:女性
     */
    @NotNull
    private Integer gender;
    /**
     * 用户年龄
     */
    @NotNull
    private Integer age;
    /**
     * 用户手机号
     */
    @NotNull
    @IsMobile
    private String telphone;
    /**
     * 验证码
     */
    @NotNull
    private String otpcode;
    /**
     * 密码
     * @return
     */
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telphone;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getOtpcode() { return otpcode; }

    public void setOtpcode(String otpcode) { this.otpcode = otpcode; }
}
