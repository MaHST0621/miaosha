 package com.example.miaosha.miaosha002.error;


public enum EnumBusinessErr implements CommonError{
    //通用的错误类型 1xxxx开头
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN(10002,"未定义错误"),

    //业务相关的错误类型 2xxxx开头
    USER_NOT_EXIST(20001,"用户不存在"),
    ;

    private EnumBusinessErr(int errorCode,String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    private int errorCode;
    private String errorMessage;
    @Override
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public CommonError setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
