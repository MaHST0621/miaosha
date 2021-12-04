package com.example.miaosha.miaosha002.service.impl;

import com.example.miaosha.miaosha002.dao.UserInfoDao;
import com.example.miaosha.miaosha002.dao.UserPasswordDao;
import com.example.miaosha.miaosha002.entity.UserInfo;
import com.example.miaosha.miaosha002.entity.UserPassword;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoDao userInfoDao;
    @Autowired
    UserPasswordDao  userPasswordDao;

    @Override
    public UserModel getUserById(Integer id) {
        UserInfo userInfo = userInfoDao.queryById(id);
        if (userInfo == null) {
            return null;
        }
        UserPassword userPassword = userPasswordDao.queryById(id);
        return convertFromDataObject(userInfo,userPassword);
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword) {
        final UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);
        userModel.setEncryptpassword(userPassword.getEncrptPassword());
        return userModel;
    }
}
