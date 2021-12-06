package com.example.miaosha.miaosha001.responseEmpy;

public class ResponseType {
    private String result;
    private Object data;

    public static ResponseType create(Object oj) {
        return create(oj,"success");
    }

    private static ResponseType create(Object oj ,String result) {
        final ResponseType response = new ResponseType();
        response.setData(oj);
        response.setResult(result);
        return response;
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
