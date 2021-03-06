package com.example.miaosha.miaosha001.dao;

import com.example.miaosha.miaosha001.dataobject.StockOrderDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StockOrderDoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    int insert(StockOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    int insertSelective(StockOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    StockOrderDo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    int updateByPrimaryKeySelective(StockOrderDo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table stock_order
     *
     * @mbg.generated Mon Dec 06 22:32:57 CST 2021
     */
    int updateByPrimaryKey(StockOrderDo record);
}