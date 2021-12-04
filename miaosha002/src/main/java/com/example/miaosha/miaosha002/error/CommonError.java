package com.example.miaosha.miaosha002.error;

public interface CommonError {
    public int getErrorCode();
    public String getErrorMessage();
    public CommonError setErrorMessage(String errorMessage);
}
