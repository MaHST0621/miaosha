package com.example.miaosha.miaosha002.controller;

import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.response.CommonReturnType;
import com.example.miaosha.miaosha002.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/goods")
//ajax 跨域注解
@CrossOrigin(origins = "*",allowCredentials = "true")
@Slf4j
public class GoodsController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "hello";
    }


    @RequestMapping("/toList")
    @ResponseBody
    public CommonReturnType toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String token) {
        if (StringUtils.isEmpty(token)) {
            return CommonReturnType.create("login","false");
        }

        UserModel userModel = userService.getUserByToken(token,request,response);
        if (userModel == null) {
            return CommonReturnType.create("login","false");
        }
        model.addAttribute("user",userModel);
        return CommonReturnType.create("goodsList");
    }
}
