package com.example.miaosha.miaosha002;


import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.service.UserService;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"com.example.miaosha.miaosha002"})
@MapperScan("com.example.miaosha.miaosha002.dao")
@RestController
public class MiaoSha002
{
    public static void main( String[] args )
    {
        SpringApplication.run(MiaoSha002.class);
    }
}
