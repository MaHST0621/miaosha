package com.example.miaosha.miaosha001.redis;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImp implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    // 向 Redis 写入一个 KEY/VALUE
    @Override
    public void setValue(String key, String value) {
        if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
            throw new IllegalArgumentException("key或value不能为空");
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }

    // 从 Redis 获取指定 KEY 的值
    @Override
    public String getValue(String key) {
        if (StringUtils.isBlank(key)) {
            throw new IllegalArgumentException("key不能为空");
        }
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

}
