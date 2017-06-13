package com.zl.fiight.entity;

/**
 * Created by Administrator on 2017/5/28.
 */
public class Entity_Personal {

    private String message;
    private User mUser;

    public Entity_Personal(String message, User user) {
        this.message = message;
        mUser = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User user) {
        mUser = user;
    }
}
