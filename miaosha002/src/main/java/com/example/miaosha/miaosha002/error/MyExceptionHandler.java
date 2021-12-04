package com.example.miaosha.miaosha002.error;

import com.example.miaosha.miaosha002.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class MyExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public Object handleException(HttpServletRequest request,Exception ex) {
        HashMap<String, Object> hashMap = new HashMap();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
            hashMap.put("errCode",businessException.getErrorCode());
            hashMap.put("errMessage",businessException.getErrorMessage());
        }else {
            hashMap.put("errCode",EnumBusinessErr.UNKNOWN.getErrorCode());
            hashMap.put("errMessage",EnumBusinessErr.UNKNOWN.getErrorMessage());
        }
        return CommonReturnType.create(hashMap,"faile");
    }
}
