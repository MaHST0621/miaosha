package com.example.miaosha.miaosha001.service.impl;


import com.example.miaosha.miaosha001.dao.StockDao;
import com.example.miaosha.miaosha001.entity.Stock;
import com.example.miaosha.miaosha001.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Stock)表服务实现类
 *
 * @author makejava
 * @since 2021-12-04 14:11:47
 */
@Service("stockService")
public class StockServiceImpl implements StockService {
    @Resource
    private StockDao stockDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Stock queryById(Integer id) {
        return this.stockDao.queryById(id);
    }

    /**
     * 分页查询
     *
     * @param stock 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @Override
    public Page<Stock> queryByPage(Stock stock, PageRequest pageRequest) {
        long total = this.stockDao.count(stock);
        return new PageImpl<>(this.stockDao.queryAllByLimit(stock, pageRequest), pageRequest, total);
    }

    /**
     * 新增数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public Stock insert(Stock stock) {
        this.stockDao.insert(stock);
        return stock;
    }

    /**
     * 修改数据
     *
     * @param stock 实例对象
     * @return 实例对象
     */
    @Override
    public Stock update(Stock stock) {
        this.stockDao.update(stock);
        return this.queryById(stock.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.stockDao.deleteById(id) > 0;
    }
}
