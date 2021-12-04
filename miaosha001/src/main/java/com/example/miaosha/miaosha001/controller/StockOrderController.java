package com.example.miaosha.miaosha001.controller;


import com.example.miaosha.miaosha001.entity.StockOrder;
import com.example.miaosha.miaosha001.service.StockOrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (StockOrder)表控制层
 *
 * @author makejava
 * @since 2021-12-04 14:11:55
 */
@RestController
@RequestMapping("stockOrder")
public class StockOrderController {
    /**
     * 服务对象
     */
    @Resource
    private StockOrderService stockOrderService;

    /**
     * 分页查询
     *
     * @param stockOrder 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<StockOrder>> queryByPage(StockOrder stockOrder, PageRequest pageRequest) {
        return ResponseEntity.ok(this.stockOrderService.queryByPage(stockOrder, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<StockOrder> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.stockOrderService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param stockOrder 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<StockOrder> add(StockOrder stockOrder) {
        return ResponseEntity.ok(this.stockOrderService.insert(stockOrder));
    }

    /**
     * 编辑数据
     *
     * @param stockOrder 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<StockOrder> edit(StockOrder stockOrder) {
        return ResponseEntity.ok(this.stockOrderService.update(stockOrder));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.stockOrderService.deleteById(id));
    }

}

