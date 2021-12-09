package com.example.miaosha.miaosha001.redis;

public interface RedisService {
    void setValue(String key, String value);
    String getValue(String key);
}
