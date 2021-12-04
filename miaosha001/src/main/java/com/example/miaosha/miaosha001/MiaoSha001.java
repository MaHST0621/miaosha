package com.example.miaosha.miaosha001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.example.miaosha.miaosha001.dao")
public class MiaoSha001 {
    public static void main(String[] args) {
        SpringApplication.run(MiaoSha001.class);
    }
}
