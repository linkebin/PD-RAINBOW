package com.yidusoft.utils;


import java.util.Map;

/**
 * Created by smy on 2017/9/24.
 */
public class FileResponseData {

    private Integer code;
    private String msg;
    private Data data;

    private Map<String,Object> map;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
