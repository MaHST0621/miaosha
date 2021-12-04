package com.example.miaosha.miaosha002.controller;

import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.error.EnumBusinessErr;
import com.example.miaosha.miaosha002.error.MyExceptionHandler;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.response.CommonReturnType;
import com.example.miaosha.miaosha002.service.UserService;
import com.example.miaosha.miaosha002.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;


@RestController
@RequestMapping("/user")
public class UserController extends MyExceptionHandler {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping("/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam(name="telphone") String telphone) {
        Random rand = new Random();
        int randomInt = rand.nextInt(9999);
        randomInt += 1000;
        String otpCode = randomInt + "";

        System.out.println("telphone: " + telphone + " & otp: " + otpCode);
        httpServletRequest.getSession().setAttribute(telphone,otpCode);

        return CommonReturnType.create(null);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            BusinessException b =  new BusinessException(EnumBusinessErr.USER_NOT_EXIST);
            throw b;
        }

        UserVo userVo = convertFromUserModel(userModel);
        return CommonReturnType.create(userVo);
    }

    private UserVo convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        final UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }

}
