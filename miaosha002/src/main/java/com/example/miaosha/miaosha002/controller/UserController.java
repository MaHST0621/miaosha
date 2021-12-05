package com.example.miaosha.miaosha002.controller;

import com.alibaba.druid.util.StringUtils;
import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.error.EnumBusinessErr;
import com.example.miaosha.miaosha002.error.MyExceptionHandler;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.response.CommonReturnType;
import com.example.miaosha.miaosha002.service.UserService;
import com.example.miaosha.miaosha002.vo.UserVo;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Random;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController {

    @Autowired
    UserService userService;
    @Autowired
    HttpServletRequest httpServletRequest;

    @PostMapping(value = "/rigist",consumes = {CONTEXT_TYPE_FORWORDS})
    @ResponseBody
    public CommonReturnType register(@RequestParam(name="telphone") String telphone,
                                     @RequestParam(name="name") String name,
                                     @RequestParam(name="age") Integer age,
                                     @RequestParam(name="gender") Integer gender,
                                     @RequestParam(name="otpcode") String otpCode,
                                     @RequestParam(name="password") String password) throws BusinessException {
        //验证otp是否正确
        String otpCodeString = String.valueOf(this.httpServletRequest.getSession().getAttribute("telphone"));
        if (StringUtils.equals(otpCodeString,otpCode)) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"短信验证码不正确");
        }

        //注册流程
        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(age);
        userModel.setTelephone(telphone);
        userModel.setEncryptpassword(MD5Encoder.encode(password.getBytes()));
        userModel.setGender(gender);
        userModel.setRegisterMod("byphone");
        userModel.setThirdPartyId("0");

        userService.rigister(userModel);

        return CommonReturnType.create(null);
    }


    @PostMapping(value = "/getotp",consumes = {CONTEXT_TYPE_FORWORDS})
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
