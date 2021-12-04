package com.example.miaosha.miaosha001.service.impl;


import com.example.miaosha.miaosha001.dao.StockOrderDao;
import com.example.miaosha.miaosha001.entity.StockOrder;
import com.example.miaosha.miaosha001.service.StockOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (StockOrder)表服务实现类
 *
 * @author makejava
 * @since 2021-12-04 14:11:55
 */
@Service("stockOrderService")
public class StockOrderServiceImpl implements StockOrderService {
    @Resource
    private StockOrderDao stockOrderDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public StockOrder queryById(Integer id) {
        return this.stockOrderDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param stockOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<StockOrder> queryByPage(StockOrder stockOrder, PageRequest pageRequest) {
        long total = this.stockOrderDao.count(stockOrder);
        return new PageImpl<>(this.stockOrderDao.queryAllByLimit(stockOrder, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    @Override
    public StockOrder insert(StockOrder stockOrder) {
        this.stockOrderDao.insert(stockOrder);
        return stockOrder;
    }

    /**
     * 修改数据
     *
     * @param stockOrder 实例对象
     * @return 实例对象
     */
    @Override
    public StockOrder update(StockOrder stockOrder) {
        this.stockOrderDao.update(stockOrder);
        return this.queryById(stockOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stockOrderDao.deleteById(id) > 0;
    }
}
