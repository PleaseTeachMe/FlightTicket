package com.zl.fiight.entity;

import cn.bmob.v3.BmobUser;

/**
 * Created by Administrator on 2017/5/24.
 */
public class User extends BmobUser {
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


}
