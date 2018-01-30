package com.jbase.helper;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.jbase.helper.base.BaseActivity;
import com.jbase.helper.view.ObservableScrollView;
import com.jbase.helper.view.TitleBar;

public class Test2Activity extends BaseActivity {
    private int maxHeight = -1;
    //

    @Override
    public int getLayoutId() {
        return R.layout.activity_test2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        /**
         * 启用刷新
         *
         */
        setRefreshLayoutEnable(true);
//        setLoadMoreLayoutEnable(true);
        /**
         * 自定义TitleBar演示
         */
        titleBar.Builder().setBackgroundPressState()
                .setBackUpEnable(false)
                .addTabString("首页")
                .addTabString("社群")
                .addTabImage(R.mipmap.ic_launcher, TitleBar.Type.Right)
                .addTabString("提交", TitleBar.Type.Right)
                .addTabString("看", TitleBar.Type.Right)
                .addTabString("什么生活", TitleBar.Type.Right)
                .setOnTabClickListener(new TitleBar.TabClickLstener() {
                    @Override
                    public void onClick(View view, int id, String title) {
                        if(!TextUtils.isEmpty(title)){
                            Toast.makeText(activity,title,Toast.LENGTH_SHORT).show();
                        }else if(R.mipmap.ic_launcher==id){
                            Toast.makeText(activity,"id = "+id,Toast.LENGTH_LONG).show();
                            startActivity(new Intent(activity,TestActivity.class));
                        }
                    }
                })
                .create();

        setByHead(findViewById(R.id.byTopView));

    }



    private void setByHead(final View view){
//view加载完成时回调

        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // TODO Auto-generated method stub
                final int[] sxy = new int[2];
                view.getLocationOnScreen(sxy);
                maxHeight = sxy[1]-paddingTop;
            }
        });
        ObservableScrollView scrollView = (ObservableScrollView) findViewById(R.id.myScrollView);
        scrollView.setMyOnScrollChangedListener(new ObservableScrollView.OnScrollChangedListener() {
            @Override
            public void onScrollChanged(int l, int y, int oldl, int oldt) {

                if (y <= 0) {   //设置标题的背景颜色
                    easeTopView.setBackgroundColor(Color.argb((int) 0, 144,151,166));
                } else if (y > 0 && y <= maxHeight) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
                    float scale = (float) y / maxHeight;

                    float alpha = (255 * scale);
                    easeTopView.setBackgroundColor(Color.argb((int) alpha, 144,151,166));
                } else {    //滑动到banner下面设置普通颜色
                    easeTopView.setBackgroundColor(Color.argb((int) 255, 144,151,166));
                }



            }
        });
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
