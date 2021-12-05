package com.example.miaosha.miaosha002.controller;

import com.example.miaosha.miaosha002.error.MyExceptionHandler;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BaseController  {
    public static final String CONTEXT_TYPE_FORWORDS = "application/x-www-form-urlencoded";

    public String EncodByMd5(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder encoder = new BASE64Encoder();

        String result = encoder.encode(md5.digest(password.getBytes("UTF-8")));
        return result;
    }
}
