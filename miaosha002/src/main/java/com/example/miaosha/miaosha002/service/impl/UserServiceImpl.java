package com.example.miaosha.miaosha002.service.impl;

import com.example.miaosha.miaosha002.dao.UserInfoDao;
import com.example.miaosha.miaosha002.dao.UserPasswordDao;
import com.example.miaosha.miaosha002.entity.UserInfo;
import com.example.miaosha.miaosha002.entity.UserPassword;
import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.error.EnumBusinessErr;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserPassword userPassword = userPasswordDao.queryByUserId(id);
        return convertFromDataObject(userInfo,userPassword);
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
                || !StringUtils.isNotEmpty(userModel.getTelephone())) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"用户基本信息务必要填");
        }

        UserInfo userInfo = convertFromUserModel(userModel);
        try {
            userInfoDao.insert(userInfo);
        }catch (DuplicateKeyException exception) {
            throw new BusinessException(EnumBusinessErr.PARAMETER_VALIDATION_ERROR,"手机号重复被注册");
        }
        userModel.setId(userInfo.getId());
        UserPassword userPassword = convertToUserPassword(userModel);
        userPasswordDao.insert(userPassword);
    }

    @Override
    public UserModel validateLogin(String telphone, String encrptPassword) throws BusinessException {
        UserInfo userInfo = userInfoDao.queryByTelphone(telphone);
        if (userInfo == null) {
            throw new BusinessException(EnumBusinessErr.USER_LOGIN_ERROR);
        }
        UserPassword userPassword = userPasswordDao.queryByUserId(userInfo.getId());

        //验证密码的正确性
        if (!StringUtils.equals(encrptPassword,userPassword.getEncrptPassword())) {
            throw new BusinessException(EnumBusinessErr.USER_LOGIN_ERROR);
        }

        return convertFromDataObject(userInfo,userPassword);
    }

    private UserPassword convertToUserPassword(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncryptpassword());
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    private  UserInfo convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel,userInfo);
        return userInfo;
    }

    private UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword) {
        final UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);
        userModel.setEncryptpassword(userPassword.getEncrptPassword());
        return userModel;
    }
}
