package com.example.miaosha.miaosha002.util;

import com.example.miaosha.miaosha002.entity.UserInfo;
import com.example.miaosha.miaosha002.entity.UserPassword;
import com.example.miaosha.miaosha002.model.UserModel;
import com.example.miaosha.miaosha002.vo.UserVo;
import org.springframework.beans.BeanUtils;

public class ConverUtils {
    /**
     * 从UserModel中获取UserPassword需要的属性
     * @param userModel
     * @return UserPassword
     */
    public static UserPassword convertToUserPassword(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserPassword userPassword = new UserPassword();
        userPassword.setEncrptPassword(userModel.getEncryptpassword());
        userPassword.setUserId(userModel.getId());
        return userPassword;
    }

    /**
     * @param userModel
     * @return UserInfo
     */
    public static UserInfo convertFromUserModel(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        UserInfo userInfo = new UserInfo();
        BeanUtils.copyProperties(userModel,userInfo);
        return userInfo;
    }

    /**
     *
     * @param userInfo
     * @param userPassword
     * @return UserModel
     */
    public static UserModel convertFromDataObject(UserInfo userInfo, UserPassword userPassword) {
        final UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userInfo,userModel);
        userModel.setEncryptpassword(userPassword.getEncrptPassword());
        return userModel;
    }

    /**
     *
     * @param userModel
     * @return
     */
    public static UserVo convertFromUserModeltoUserVo(UserModel userModel) {
        if (userModel == null) {
            return null;
        }
        final UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userModel,userVo);
        return userVo;
    }
}
