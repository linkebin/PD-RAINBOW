package com.yidusoft.redisMq;

/**
 * Created by linkb on 2018/5/2.
 */
public class Message {

    private String type;

    private Object object;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
