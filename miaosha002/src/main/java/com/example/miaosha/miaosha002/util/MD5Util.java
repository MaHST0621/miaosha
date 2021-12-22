package com.example.miaosha.miaosha002.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static final String saltH = "@@@miaosha####";

    public static String inputPasswordToFromPassword(String inputPassword) {
        String str_salt = saltH.charAt(0) + saltH.charAt(3) + inputPassword + saltH.charAt(5);

        return md5(str_salt);
    }


    public static String fromPasswordToDbPassword(String fromPassword,String salt) {
        String str_salt = salt.charAt(0) + salt.charAt(3) + fromPassword + salt.charAt(5);
        return md5(str_salt);
    }

    public static String inputPasswordToDbPassWord(String inputPassword,String salt) {
        String fromPassword = inputPasswordToFromPassword(inputPassword);
        String dbPassword = fromPasswordToDbPassword(fromPassword,"1a2b3c4d");
        return dbPassword;
    }


    public static void main(String[] args) {
        System.out.println(inputPasswordToFromPassword("123456"));
        System.out.println(fromPasswordToDbPassword("0d59ad3c7f5b10f2025e60027ae704a2","1a2b3c4d"));
        System.out.println(inputPasswordToDbPassWord("123456","1a2b3c4d"));
    }

}
