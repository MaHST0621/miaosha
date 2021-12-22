package com.example.miaosha.miaosha002.service.impl;

import com.example.miaosha.miaosha002.dao.UserInfoDao;
import com.example.miaosha.miaosha002.dao.UserPasswordDao;
import com.example.miaosha.miaosha002.entity.UserInfo;
import com.example.miaosha.miaosha002.entity.UserPassword;
import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.error.EnumBusinessErr;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.service.UserService;
import com.example.miaosha.miaosha002.util.ConverUtils;
import com.example.miaosha.miaosha002.util.CookieUtils;
import com.example.miaosha.miaosha002.util.UUIDUtils;
import com.example.miaosha.miaosha002.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserPasswordDao  userPasswordDao;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public UserModel getUserById(Integer id) {
        UserInfo userInfo = userInfoDao.queryById(id);
        if (userInfo == null) {
            return null;
        }
        UserPassword userPassword = userPasswordDao.queryByUserId(id);
        return ConverUtils.convertFromDataObject(userInfo,userPassword);
    }

    @Override
    @Transactional
    public void rigister(UserModel userModel) throws BusinessException {
        if (userModel == null) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR);
        }

        if (!StringUtils.isNotEmpty(userModel.getName())
                || userModel.getAge() == null
                || userModel.getGender() == null
                || !StringUtils.isNotEmpty(userModel.getTelphone())) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"用户基本信息务必要填");
        }

        UserInfo userInfo = ConverUtils.convertFromUserModel(userModel);
        try {
            userInfoDao.insert(userInfo);
        }catch (DuplicateKeyException exception) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"手机号重复被注册");
        }
        userModel.setId(userInfo.getId());
        UserPassword userPassword = ConverUtils.convertToUserPassword(userModel);
        userPasswordDao.insert(userPassword);
    }

    @Override
    public UserModel validateLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) throws BusinessException {
        UserInfo userInfo = userInfoDao.queryByTelphone(loginVo.getTelphone());
        if (userInfo == null) {
            throw new BusinessException(EnumBusinessErr.USER_LOGIN_ERROR);
        }
        UserPassword userPassword = userPasswordDao.queryByUserId(userInfo.getId());

        //验证密码的正确性
        if (!StringUtils.equals(loginVo.getPassword(),userPassword.getEncrptPassword())) {
            throw new BusinessException(EnumBusinessErr.USER_LOGIN_ERROR);
        }

        UserModel userModel = ConverUtils.convertFromDataObject(userInfo,userPassword);

//        /**
//         * 第一版本  Cookie ——》 Session
//         */
//        String ticket = UUIDUtils.uuid();
//        request.getSession().setAttribute(ticket,userModel);
//        System.out.println(ticket);
//        CookieUtils.setCookie(request,response,"userTicket",ticket);

        /**
         * 第二版本 redis ---》 token
         */
        String ticket = UUIDUtils.uuid();
        //用户信息存入redis
        redisTemplate.opsForValue().set("user: " + ticket,userModel);
        //响应头加入 set-cookie
        CookieUtils.setCookie(request,response,"userTicket",ticket);

        return userModel;
    }

    @Override
    public UserModel getUserByToken(String userToken,HttpServletRequest request,HttpServletResponse response) {
        if (StringUtils.isEmpty(userToken)) {
            return null;
        }
        final UserModel userModel = (UserModel) redisTemplate.opsForValue().get("user: " + userToken);

        if(userModel != null) {
            CookieUtils.setCookie(request,response,"userTicket",userToken);
        }
        return userModel;
    }

}
