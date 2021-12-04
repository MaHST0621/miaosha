package com.example.miaosha.miaosha002.vo;

public class UserVo {
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
}
