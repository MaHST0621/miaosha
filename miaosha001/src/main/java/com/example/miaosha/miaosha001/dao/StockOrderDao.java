package com.example.miaosha.miaosha001.dao;


import com.example.miaosha.miaosha001.entity.StockOrder;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * (StockOrder)表数据库访问层
 *
 * @author makejava
 * @since 2021-12-04 14:11:55
 */
@Mapper
public interface StockOrderDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockOrder queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param stockOrder 查询条件
     * @param pageable         分页对象
     * @return 对象列表
     */
    List<StockOrder> queryAllByLimit(StockOrder stockOrder, @Param("pageable") Pageable pageable);

    /**
     * 统计总行数
     *
     * @param stockOrder 查询条件
     * @return 总行数
     */
    long count(StockOrder stockOrder);

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 影响行数
     */
    int insert(StockOrder stockOrder);

    /**
     * 批量新增数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockOrder> 实例对象列表
     * @return 影响行数
     */
    int insertBatch(@Param("entities") List<StockOrder> entities);

    /**
     * 批量新增或按主键更新数据（MyBatis原生foreach方法）
     *
     * @param entities List<StockOrder> 实例对象列表
     * @return 影响行数
     * @throws org.springframework.jdbc.BadSqlGrammarException 入参是空List的时候会抛SQL语句错误的异常，请自行校验入参
     */
    int insertOrUpdateBatch(@Param("entities") List<StockOrder> entities);

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 影响行数
     */
    int update(StockOrder stockOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}

