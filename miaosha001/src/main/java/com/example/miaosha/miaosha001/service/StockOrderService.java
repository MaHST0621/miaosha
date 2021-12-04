package com.example.miaosha.miaosha001.service;


import com.example.miaosha.miaosha001.entity.StockOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * (StockOrder)表服务接口
 *
 * @author makejava
 * @since 2021-12-04 14:11:55
 */
public interface StockOrderService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    StockOrder queryById(Integer id);

    /**
     * 分页查询
     *
     * @param stockOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    Page<StockOrder> queryByPage(StockOrder stockOrder, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    StockOrder insert(StockOrder stockOrder);

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    StockOrder update(StockOrder stockOrder);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}
