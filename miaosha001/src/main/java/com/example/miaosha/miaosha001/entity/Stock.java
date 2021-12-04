package com.example.miaosha.miaosha001.entity;

import java.io.Serializable;

/**
 * (Stock)实体类
 *
 * @author makejava
 * @since 2021-12-04 14:11:38
 */
public class Stock implements Serializable {
    private static final long serialVersionUID = -21287266025695112L;
    
    private Integer id;
    /**
     * 名称
     */
    private String name;
    /**
     * 库存
     */
    private Integer count;
    /**
     * 已售
     */
    private Integer sale;
    /**
     * 乐观锁，版本号
     */
    private Integer version;


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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}

