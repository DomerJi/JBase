package com.jbase.helper.net;

/**
 * Created by aaa on 2017/8/11.
 */

public interface ConnectionListener {

    void onConnected();

    void onDisconnected(int var1);

    void onNetTypeChanged(Type var1);

    enum Type{
        NO,
        WIFI,
        GPRS;
    }

}
