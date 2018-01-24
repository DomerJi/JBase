package com.jbase.helper;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.jbase.helper.base.BaseActivity;
import com.jbase.helper.view.TitleBar;

public class Test2Activity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_test2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        /**
         * 启用刷新
         */
        setRefreshLayoutEnable(true);
        /**
         * 自定义TitleBar演示
         */
        titleBar.Builder().setBackgroundPressState()
                .setBackUpEnable(true)
                .addTabString("首页")
                .addTabString("社群")
                .addTabImage(R.mipmap.ic_launcher, TitleBar.Type.Right,false)
                .addTabString("提交", TitleBar.Type.Right)
                .addTabString("看", TitleBar.Type.Right)
                .addTabString("什么生活", TitleBar.Type.Right)
                .setOnTabClickListener(new TitleBar.TabClickLstener() {
                    @Override
                    public void onClick(View view, int id, String title) {
                        if(!TextUtils.isEmpty(title)){
                            Toast.makeText(activity,title,Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create();

    }

    @Override
    protected View getPaddingTopByView(View view) {
        return view.findViewById(R.id.topImage);
//        return null;
    }

    @Override
    public void initListener() {

    }

    /**
     * 重写是否显示TitleBar  默认显示
     * @return
     */
    @Override
    protected boolean getTitleBarVisible() {
        return true;
    }
}
