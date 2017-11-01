package com.jbase.helper.user;

/**
 * Created by aaa on 2017/8/11.
 */

public interface UserLoginListener {

    void onLogin(IUser user);

    void onUserChanged(IUser oldUser,IUser newUser);

    void onUserMessageChanged(IUser newUser,UserMessageType type);

    void onBackLogin(IUser user);

    enum UserMessageType{
        AVATER,
        NICK,
        SIGNATURE,
        ADDRESS,
        SEX,
        PHONE,
        EMAIL,
        COMPANY,
        URL,
        APPNUMBER
    }

}
