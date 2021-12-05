package com.example.miaosha.miaosha002.service;

import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.model.UserModel;

public interface UserService {
    UserModel getUserById(Integer id);
    void rigister(UserModel userModel) throws BusinessException;

    /**
     * @param telphone
     * @param encrptPassword :加密后的密码
     * @throws BusinessException
     */
    UserModel validateLogin(String telphone,String encrptPassword) throws BusinessException;
}
