package com.example.miaosha.miaosha002.service;

import com.example.miaosha.miaosha002.error.BusinessException;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserService {
    /**
     *
     * @param id
     * @return
     */
    UserModel getUserById(Integer id);

    /**
     *
     * @param userModel
     * @throws BusinessException
     */
    void rigister(UserModel userModel) throws BusinessException;

    /**
     * @throws BusinessException
     */
    UserModel validateLogin(LoginVo loginVo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws BusinessException;

    UserModel getUserByToken(String userToken,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse);
}
