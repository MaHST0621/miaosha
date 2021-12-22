package com.example.miaosha.miaosha002.model;

public class UserModel {
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
    private String telphone;
    /**
     * 注册类型: byphone;bywechat;byalipay
     */
    private String registerMod;
    /**
     * 第三方插件Id
     */
    private String thirdPartyId;

    private String encryptpassword;

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

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
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

    public String getEncryptpassword() {
        return encryptpassword;
    }

    public void setEncryptpassword(String encryptpassword) {
        this.encryptpassword = encryptpassword;
    }
}
