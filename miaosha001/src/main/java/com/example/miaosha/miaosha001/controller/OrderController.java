package com.example.miaosha.miaosha001.controller;

import com.example.miaosha.miaosha001.dataobject.StockOrderDo;
import com.example.miaosha.miaosha001.responseEmpy.ResponseType;
import com.example.miaosha.miaosha001.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello world";
    }

    @RequestMapping("/create")
    @ResponseBody
    public ResponseType createOreder(@RequestBody HashMap<String,String> map) {
        Integer userId = Integer.valueOf(map.get("user_id"));
        Integer sId = Integer.valueOf(map.get("stock_id"));
        String sName = map.get("stock_name");
        StockOrderDo orderDo = new StockOrderDo();
        orderDo.setSid(sId);
        orderDo.setName(sName);
        orderDo.setUserId(userId);
        orderDo.setCreateTime(new Date());

        orderService.createWrongOrder(orderDo);
        return ResponseType.create(orderDo);
    }
}
