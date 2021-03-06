package com.example.miaosha.miaosha002.entity;

import java.io.Serializable;

/**
 * (UserInfo)实体类
 *
 * @author makejava
 * @since 2021-12-04 22:30:45
 */
public class UserInfo implements Serializable {
    /**
     * 用户id
     */
    private Integer id;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 1:男性 2:女性
     */
    private Integer gender;
    /**
     * 用户年龄
     */
    private Integer age;
    /**
     * 用户手机号
     */
    private String telephone;
    /**
     * 注册类型: byphone;bywechat;byalipay
     */
    private String registerMod;
    /**
     * 第三方插件Id
     */
    private String thirdPartyId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRegisterMod() {
        return registerMod;
    }

    public void setRegisterMod(String registerMod) {
        this.registerMod = registerMod;
    }

    public String getThirdPartyId() {
        return thirdPartyId;
    }

    public void setThirdPartyId(String thirdPartyId) {
        this.thirdPartyId = thirdPartyId;
    }

}

