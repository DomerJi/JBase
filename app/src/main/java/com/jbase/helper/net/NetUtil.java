package com.jbase.helper.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.jbase.helper.JBase;

/**
 * Created by aaa on 2017/8/11.
 */

public class NetUtil {

    private static boolean connect = true;

    private static ConnectionListener.Type type = ConnectionListener.Type.NO;

    private NetUtil(){
    }

    public static ConnectionListener.Type getType() {
        return type;
    }

    public static void setType(ConnectionListener.Type type) {
        NetUtil.type = type;
    }

    public static boolean isConnect() {
        return connect;
    }

    public static void setConnect(boolean connect) {
        NetUtil.connect = connect;
    }

    public static void init(Context context){
        boolean success = false;
        //获得网络连接服务
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取wifi连接状态
        NetworkInfo.State state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState();
        //判断是否正在使用wifi网络
        if (state == NetworkInfo.State.CONNECTED) {
            success = true;
            for(ConnectionListener connectionListeners: JBase.getInstance().getConnectionListeners()){
                if(NetUtil.isConnect()!=success){
                    connectionListeners.onConnected();
                }
                if(NetUtil.getType()!=ConnectionListener.Type.WIFI) {
                    connectionListeners.onNetTypeChanged(ConnectionListener.Type.WIFI);
                }
            }
            NetUtil.setType(ConnectionListener.Type.WIFI);
        }
        //获取GPRS状态
        state = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState();
        //判断是否在使用GPRS网络
        if (state == NetworkInfo.State.CONNECTED) {
            success = true;
            for(ConnectionListener connectionListeners:JBase.getInstance().getConnectionListeners()){
                if(NetUtil.isConnect()!=success){
                    connectionListeners.onConnected();
                }
                if(NetUtil.getType()!=ConnectionListener.Type.GPRS) {
                    connectionListeners.onNetTypeChanged(ConnectionListener.Type.GPRS);
                }
            }
            NetUtil.setType(ConnectionListener.Type.GPRS);
        }
        //如果没有连接成功
        if(!success){
            for(ConnectionListener connectionListeners:JBase.getInstance().getConnectionListeners()){
                if(NetUtil.isConnect()!=success){
                    connectionListeners.onDisconnected(0);
                }
                if(NetUtil.getType()!=ConnectionListener.Type.NO) {
                    connectionListeners.onNetTypeChanged(ConnectionListener.Type.NO);
                }
            }
            NetUtil.setType(ConnectionListener.Type.NO);
            Toast.makeText(context,"当前网络无连接",Toast.LENGTH_SHORT).show();
        }

        NetUtil.setConnect(success);
    }


}
