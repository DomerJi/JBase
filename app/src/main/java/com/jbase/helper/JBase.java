package com.jbase.helper;

import android.content.Context;

import com.jbase.helper.net.ConnectionListener;
import com.jbase.helper.net.NetUtil;
import com.jbase.helper.user.UserLoginListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by aaa on 2017/8/11.
 */

public class JBase {

    private static JBase instance;
    public Context context;

    public boolean addconnectioned = false;
    private List<ConnectionListener> connectionListeners = Collections.synchronizedList(new ArrayList());
    private List<UserLoginListener> userLoginListeners = Collections.synchronizedList(new ArrayList());

    private JBase(){
    }

    public static JBase getInstance(){
        if(null == instance){
            instance = new JBase();
        }
        return instance;
    }

    public void init(Context context){
        this.context = context.getApplicationContext();
    }

    public Context getContext() {
        return context;
    }

    public List<ConnectionListener> getConnectionListeners() {
        return connectionListeners;
    }

    public void setConnectionListeners(List<ConnectionListener> connectionListeners) {
        this.connectionListeners = connectionListeners;
    }

    public List<UserLoginListener> getUserLoginListeners() {
        return userLoginListeners;
    }

    public void setUserLoginListeners(List<UserLoginListener> userLoginListeners) {
        this.userLoginListeners = userLoginListeners;
    }

    public void addConnectionListener(ConnectionListener var1){
        if(null == var1){
            return;
        }

        synchronized(this.connectionListeners) {
            if(!this.connectionListeners.contains(var1)) {
                this.connectionListeners.add(var1);
            }
        }

        if(!addconnectioned){
            NetUtil.init(getContext());
            addconnectioned = true;
        }
    }

    public void removeConnectionListener(ConnectionListener var1){
        if(null == var1){
            return;
        }

        synchronized(this.connectionListeners) {
            if(this.connectionListeners.contains(var1)) {
                this.connectionListeners.remove(var1);
            }
        }
    }

    public void addUserLoginListener(UserLoginListener var1){
        if(null == var1){
            return;
        }
        synchronized(this.userLoginListeners) {
            if(!this.userLoginListeners.contains(var1)) {
                this.userLoginListeners.add(var1);
            }
        }
    }

    public void removeUserLoginListener(ConnectionListener var1){
        if(null == var1){
            return;
        }

        synchronized(this.userLoginListeners) {
            if(this.userLoginListeners.contains(var1)) {
                this.userLoginListeners.remove(var1);
            }
        }
    }

}
