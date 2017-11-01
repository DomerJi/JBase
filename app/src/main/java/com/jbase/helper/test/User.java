package com.jbase.helper.test;

import com.jbase.helper.user.IUser;

/**
 * Created by aaa on 2017/8/11.
 */

public class User implements IUser{

    public String username;

    public User(String username) {
        this.username = username;
    }

    @Override
    public String getUserId() {
        return username;
    }
}
