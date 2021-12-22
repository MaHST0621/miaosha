package com.example.miaosha.miaosha002.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.error.EnumBusinessErr;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.response.CommonReturnType;
import com.example.miaosha.miaosha002.service.UserService;
import com.example.miaosha.miaosha002.util.ConverUtils;
import com.example.miaosha.miaosha002.util.MD5Util;
import com.example.miaosha.miaosha002.validator.IsMobile;
import com.example.miaosha.miaosha002.vo.LoginVo;
import com.example.miaosha.miaosha002.vo.RegisterVo;
import com.example.miaosha.miaosha002.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*",allowCredentials = "true")
@Slf4j
public class UserController extends BaseController{
    @Autowired
    UserService userService;

    @PostMapping(value = "/rigist")
    @ResponseBody
    public CommonReturnType register(@Valid RegisterVo registerVo,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //验证otp是否正确
        String otpCodeString = httpServletRequest.getSession().getAttribute(registerVo.getTelephone()) + "";
        System.out.println(otpCodeString);
        if (!StringUtils.equals(otpCodeString, registerVo.getOtpcode())) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");
        }

        //注册流程
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(registerVo,userModel);
        userModel.setRegisterMod("byphone");
        userModel.setThirdPartyId("2");
        String Encryptpassword = MD5Util.inputPasswordToDbPassWord(registerVo.getPassword(),"1a2b3c4d");
        userModel.setEncryptpassword(Encryptpassword);

        userService.rigister(userModel);
        return CommonReturnType.create(null);
    }


    @PostMapping(value = "/getotp")
    @ResponseBody
    public CommonReturnType getOtp(@IsMobile String telphone,HttpServletRequest httpServletRequest) {
        Random rand = new Random();
        int randomInt = rand.nextInt(9999);
        randomInt += 1000;
        String otpCode = randomInt + "";

        System.out.println("telphone: " + telphone + " & otp: " + otpCode);
        httpServletRequest.getSession().setAttribute(telphone,otpCode);

        return CommonReturnType.create(null);
    }

    @PostMapping(value = "/login")
    @ResponseBody
    public CommonReturnType login(LoginVo loginVo,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        System.out.println(loginVo.toString());
        loginVo.setPassword(MD5Util.inputPasswordToDbPassWord(loginVo.getPassword(),"1a2b3c4d"));
        UserModel userModel = userService.validateLogin(loginVo,httpServletRequest,httpServletResponse);

        return CommonReturnType.create(userModel);
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name="id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if (userModel == null) {
            throw new BusinessException(EnumBusinessErr.USER_NOT_EXIST);
        }
        UserVo userVo = ConverUtils.convertFromUserModeltoUserVo(userModel);
        return CommonReturnType.create(userVo);
    }


}
