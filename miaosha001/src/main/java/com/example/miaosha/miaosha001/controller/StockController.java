package com.example.miaosha.miaosha001.controller;


import com.example.miaosha.miaosha001.entity.Stock;
import com.example.miaosha.miaosha001.service.StockService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (Stock)表控制层
 *
 * @author makejava
 * @since 2021-12-04 14:11:33
 */
@RestController
@RequestMapping("stock")
public class StockController {
    /**
     * 服务对象
     */
    @Resource
    private StockService stockService;

    @GetMapping("/test")
    public String get() {
        return "hello world!";
    }
    /**
     * 分页查询
     *
     * @param stock 筛选条件
     * @param pageRequest      分页对象
     * @return 查询结果
     */
    @GetMapping
    public ResponseEntity<Page<Stock>> queryByPage(Stock stock, PageRequest pageRequest) {
        return ResponseEntity.ok(this.stockService.queryByPage(stock, pageRequest));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public ResponseEntity<Stock> queryById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.stockService.queryById(id));
    }

    /**
     * 新增数据
     *
     * @param stock 实体
     * @return 新增结果
     */
    @PostMapping
    public ResponseEntity<Stock> add(Stock stock) {
        return ResponseEntity.ok(this.stockService.insert(stock));
    }

    /**
     * 编辑数据
     *
     * @param stock 实体
     * @return 编辑结果
     */
    @PutMapping
    public ResponseEntity<Stock> edit(Stock stock) {
        return ResponseEntity.ok(this.stockService.update(stock));
    }

    /**
     * 删除数据
     *
     * @param id 主键
     * @return 删除是否成功
     */
    @DeleteMapping
    public ResponseEntity<Boolean> deleteById(Integer id) {
        return ResponseEntity.ok(this.stockService.deleteById(id));
    }

}

