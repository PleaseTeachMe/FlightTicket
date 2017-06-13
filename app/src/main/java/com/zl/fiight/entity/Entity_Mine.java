package com.zl.fiight.entity;

/**
 * Created by Administrator on 2017/5/20.
 */
public class Entity_Mine {
    //个人中心页面设置的属性
    private int id; //图片id
    private String message; //信息

    public Entity_Mine(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
