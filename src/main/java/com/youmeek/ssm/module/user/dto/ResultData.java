package com.youmeek.ssm.module.user.dto;

import java.util.HashMap;
import java.util.Map;

public class ResultData {
    private Boolean status = true;
    private int code = 200;
    private String message;
    private Map<String,Object> data = new HashMap<>();

    public static ResultData ok() {
        return new ResultData();
    }

    public static ResultData ok(String message){ return new ResultData(message);}

    public static ResultData ok(String key, Object data) {
        return new ResultData(key,data);
    }

    public static ResultData fail() {
        return new ResultData(false, null);
    }

    public static ResultData fail(String message) {
        return new ResultData(false, message);
    }

    public static ResultData fail(String message, int code) {
        return new ResultData(false, message, code);
    }

    public static ResultData failByParam(String message) {
        return new ResultData(false, message, ResultCode.PARAM_ERROR_CODE.getCode());
    }

    public  ResultData Add(String key,Object object){
        this.data.put(key, object);
        return this;
    }

    public ResultData(String message){
        this.message = message;
    }

    public ResultData(String key, Object data) {
        super();
        this.data.put(key,data);
    }

    public ResultData(boolean status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public ResultData(boolean status, String message, int code) {
        super();
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public ResultData() {
        super();
    }

    public Boolean getStatus() {
        return status;
    }
    public void setStatus(Boolean status) {
        this.status = status;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Object getData() {
        return data;
    }

}
