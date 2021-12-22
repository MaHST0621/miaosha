package com.example.miaosha.miaosha002.controller;

import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.response.CommonReturnType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/goods")
//ajax 跨域注解
@CrossOrigin()
@Slf4j
public class GoodsController {
    @RequestMapping(value = "/test",method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        return "hello";
    }


    @RequestMapping("/toList")
    @ResponseBody
    public CommonReturnType toList(HttpSession session, Model model, @CookieValue("userTicket") String ticket) {
        if (StringUtils.isEmpty(ticket)) {
            return CommonReturnType.create("login","false");
        }

        UserModel userModel = (UserModel) session.getAttribute(ticket);
        if (userModel == null) {
            return CommonReturnType.create("login","false");
        }
        model.addAttribute("user",userModel);
        return CommonReturnType.create("goodsList");
    }
}
