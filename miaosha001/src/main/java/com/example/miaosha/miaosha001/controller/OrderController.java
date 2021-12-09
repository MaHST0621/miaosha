package com.example.miaosha.miaosha001.controller;

import com.example.miaosha.miaosha001.dataobject.StockOrderDo;
import com.example.miaosha.miaosha001.redis.RedisService;
import com.example.miaosha.miaosha001.responseEmpy.ResponseType;
import com.example.miaosha.miaosha001.service.OrderService;
import com.google.common.util.concurrent.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    RedisService redisService;

    RateLimiter rateLimiter = RateLimiter.create(10);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    @RequestMapping("/create")
    @ResponseBody
    public ResponseType createOreder(@RequestBody HashMap<String,String> map) {
        //限流器 - 配合乐观锁 ， 减缓Version的冲突率
        //rateLimiter.tryAcquire();

        Integer userId = Integer.valueOf(map.get("user_id"));
        Integer sId = Integer.valueOf(map.get("stock_id"));
        String sName = map.get("stock_name");
        StockOrderDo orderDo = new StockOrderDo();
        orderDo.setSid(sId);
        orderDo.setName(sName);
        orderDo.setUserId(userId);
        orderDo.setCreateTime(new Date());

        //乐观锁版本
        //orderService.createWrongOrder(orderDo);
        //悲观锁版本
        orderService.createPessimisticOrder(orderDo);
        return ResponseType.create(orderDo);
    }

    @RequestMapping(value = "/create_redis")
    @ResponseBody
    @Transactional
    public ResponseType createOrderByRedis(@RequestBody HashMap<String,String> map) {
        Integer userId = Integer.valueOf(map.get("user_id"));
        Integer sId = Integer.valueOf(map.get("stock_id"));
        String sName = map.get("stock_name");
        Integer count = Integer.parseInt(redisService.getValue(String.valueOf(sId)));
        if (count < 0) {
            throw new RuntimeException("库存不足了");
        }else {
            count = count - 1;
            redisService.setValue(String.valueOf(sId),String.valueOf(count));
        }
        return ResponseType.create(null);

    }
}
