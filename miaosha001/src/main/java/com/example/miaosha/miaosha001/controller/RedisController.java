package com.example.miaosha.miaosha001.controller;

import com.example.miaosha.miaosha001.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/redis")
public class RedisController {
    @Autowired
    RedisService redisService;

    @RequestMapping(value = "/test")
    public String hello() {
        return "hello redis!";
    }

    @RequestMapping(value ="/set")
    public void setRedis(@RequestParam(value ="value")String value,
                         @RequestParam(value = "key")String key) {
        redisService.setValue(key,value);
    }

    @RequestMapping(value = "get")
    public String getRedis(@RequestParam(value = "key") String key) {
        return redisService.getValue(key);
    }
}
