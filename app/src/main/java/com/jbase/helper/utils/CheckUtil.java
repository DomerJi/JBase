package com.jbase.helper.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aaa on 2017/9/11.
 */

public class CheckUtil {

    public static boolean isEmptyObjs(Object... objects){
        for(Object obj:objects){
            if(null==obj){
                return true;
            }
        }
        return false;
    }

    public static boolean isEmail(String email){
        if(TextUtils.isEmpty(email)){
            return false;
        }
        return  email.contains("@");
    }

    public static boolean isPhone(String phone){
        Pattern p = Pattern.compile("^1(6[0-9]|3[0-9]|4[0-9]|5[0-9]|8[0-9]|7[0-9])\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

}
