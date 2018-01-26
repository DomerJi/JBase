package com.jbase.helper.appliaction;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.jbase.helper.utils.AppScreenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Created by aaa on 2018/1/23.
 */

public class App extends MultiDexApplication {

    private static App app;
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(android.R.color.white, android.R.color.black);//全局设置主题颜色
                return new ClassicsHeader(context)
                        .setTimeFormat(new SimpleDateFormat("上次更新 yy-MM-dd hh:mm:ss", Locale.CHINA));//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20).setPrimaryColorId(android.R.color.darker_gray).setAccentColorId(android.R.color.white);
            }
        });
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }



    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        initConstant();
    }

    private void initConstant() {
        Constant.App.appName = getAppName(android.os.Process.myPid());

        Constant.Screen.height = AppScreenUtil.getScreenHeight(getInstance());
        Constant.Screen.width = AppScreenUtil.getScreenWidth(getInstance());
        Constant.Screen.statusBarHeight = AppScreenUtil.getStatusBarHeight(getInstance());
        Constant.Screen.statusHeight = AppScreenUtil.getStatusHeight(getInstance());

    }

    public static App getInstance(){
        return app;
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) app.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        if(list!=null){
            Iterator i = list.iterator();
            while (i.hasNext()) {
                ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
                try {
                    if (info.pid == pid) {
                        // 根据进程的信息获取当前进程的名字
                        processName = info.processName;
                        // 返回当前进程名
                        return processName;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // 没有匹配的项，返回为null
        }
        return null;
    }
}
