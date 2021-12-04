package com.example.miaosha.miaosha001.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (StockOrder)实体类
 *
 * @author makejava
 * @since 2021-12-04 14:11:55
 */
public class StockOrder implements Serializable {
    private static final long serialVersionUID = -97643506629629781L;
    
    private Integer id;
    /**
     * 库存ID
     */
    private Integer sid;
    /**
     * 商品名称
     */
    private String name;
    
    private Integer userId;
    /**
     * 创建时间
     */
    private Date createTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

