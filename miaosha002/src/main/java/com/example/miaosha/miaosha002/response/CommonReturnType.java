package com.example.miaosha.miaosha002.response;

public class CommonReturnType {

    private String result;
    private Object data;

    public static CommonReturnType create(Object oj) {
        return create(oj,"success");
    }

    public static CommonReturnType create(Object oj, String result) {
        final CommonReturnType returnType = new CommonReturnType();
        returnType.setData(oj);
        returnType.setResult(result);
        return returnType;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
