package com.jbase.helper.user;

import android.text.TextUtils;
import android.util.Log;

import com.jbase.helper.JBase;
import com.jbase.helper.save.ACache;

import java.util.ArrayList;

/**
 * Created by aaa on 2017/8/11.
 */

public class UserUtil {

    private static final String IUserHistory = "IUserHistory";

    private static IUser currentIUser = null;

    public static boolean login(IUser iUser){
        if(null==iUser||TextUtils.isEmpty(iUser.getUserId())){
            Log.e("Login","用户为空，登录失败");
            return false;
        }
        for(UserLoginListener userLoginListener:JBase.getInstance().getUserLoginListeners()){
            userLoginListener.onLogin(iUser);
            if(isLogin()&&!getCurrentUser().getUserId().equals(iUser.getUserId())){
                userLoginListener.onUserChanged(getCurrentUser(),iUser);
            }
        }

        setCurrentUser(iUser);

        Object object = ACache.get(JBase.getInstance().context).getAsObject(IUserHistory);

        if(null == object){
            ArrayList<IUser> iUsers = new ArrayList<>();
            iUsers.add(iUser);
            ACache.get(JBase.getInstance().context).put(IUserHistory,iUsers);
        }else {
            ArrayList<IUser> iUsers = (ArrayList<IUser>) object;
            if(!iUsers.contains(iUser)) {
                iUsers.add(iUser);
                ACache.get(JBase.getInstance().context).put(IUserHistory,iUsers);
            }
        }

        return true;

    }

    public static boolean loginout(){
        if(isLogin()){
            for(UserLoginListener userLoginListener:JBase.getInstance().getUserLoginListeners()){
                userLoginListener.onBackLogin(getCurrentUser());
            }
            setCurrentUser(null);
            return true;
        }else {
            Log.e("Login","未登录，无需退出");
            return false;
        }
    }

    public static boolean isLogin(){
        if(null ==  UserUtil.currentIUser) {
            Log.e("Login","未登录");
            return false;
        }
        Log.e("Login","已登录");
        return true;
    }

    private static void setCurrentUser(IUser iUser){
        UserUtil.currentIUser = iUser;
    }

    public static IUser getCurrentUser(){
        if(null == UserUtil.currentIUser){
            return null;
        }
        return UserUtil.currentIUser;
    }

    public static IUser getLastLoginUser(){

        Object object = ACache.get(JBase.getInstance().context).getAsObject(IUserHistory);

        if(null != object){
            ArrayList<IUser> iUsers = (ArrayList<IUser>) object;
            if(null != iUsers&&iUsers.size()>0) {
                IUser iUser = iUsers.get((iUsers.size()-1));
                return  iUser;
            }
        }

        return null;

    }

    public static boolean updateIUser(IUser iUser, UserLoginListener.UserMessageType type){
        Object object = ACache.get(JBase.getInstance().context).getAsObject(IUserHistory);

        if(null != object){
            ArrayList<IUser> iUsers = (ArrayList<IUser>) object;
            iUsers.set(iUsers.size()-1,iUser);
            ACache.get(JBase.getInstance().context).put(IUserHistory,iUsers);
            for(UserLoginListener userLoginListener:JBase.getInstance().getUserLoginListeners()){
                userLoginListener.onUserMessageChanged(iUser,type);
            }
            return true;
        }

        return false;
    }

}
