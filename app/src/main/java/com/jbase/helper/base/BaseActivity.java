package com.jbase.helper.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.jbase.helper.R;
import com.jbase.helper.utils.AppScreenUtil;
import com.jbase.helper.utils.DensityUtils;
import com.jbase.helper.view.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnMultiPurposeListener;

/**
 * Created by aaa on 2018/1/23.
 */

public abstract class BaseActivity extends Activity {

    protected BaseActivity activity;
    protected RelativeLayout statusBar;
    protected TitleBar titleBar;
    protected FrameLayout mRootView;
    protected int paddingTop;
    protected SmartRefreshLayout refreshLayout;
    private View easeTopView;
    private View topView;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        FrameLayout frameLayout = (FrameLayout) LayoutInflater.from(activity).inflate(R.layout.base_activity_layout,null);
        easeTopView = frameLayout.findViewById(R.id.easeTopView);
        refreshLayout = (SmartRefreshLayout) frameLayout.findViewById(R.id.refreshLayout);

        mRootView = (FrameLayout) frameLayout.findViewById(R.id.baseFrame);
        statusBar = (RelativeLayout) frameLayout.findViewById(R.id.statusBar);
        titleBar = (TitleBar) frameLayout.findViewById(R.id.easeTitleBar);

        View view = LayoutInflater.from(activity).inflate(getLayoutId(),null);
        int statusBarHeight = AppScreenUtil.getStatusHeight(activity);

        boolean titleBarVisible = getTitleBarVisible();
        if(titleBarVisible){
            titleBar.setVisibility(View.VISIBLE);
            paddingTop = statusBarHeight+ DensityUtils.dip2px(activity,44);
        }else {
            paddingTop = statusBarHeight;
            titleBar.setVisibility(View.GONE);
        }
        /**
         * 设置头部空间
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            topView = getPaddingTopByView(view);
            if(topView !=null){
                topView.setPadding(topView.getPaddingLeft(), topView.getPaddingTop()+paddingTop, topView.getPaddingRight(), topView.getPaddingBottom());
            }else {
                easeTopView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) refreshLayout.getLayoutParams();
                lp.setMargins(0,paddingTop,0,0);
                refreshLayout.setLayoutParams(lp);
            }
        }else {
            statusBar.setVisibility(View.INVISIBLE);
        }

        statusBar.setMinimumHeight(statusBarHeight);
        mRootView.addView(view);

        initState();
        setContentView(frameLayout);
        setRefreshAndLoadMoreLayoutEnable(false);
        initData(savedInstanceState);
        initListener();


    }

    public void setRefreshAndLoadMoreLayoutEnable(boolean layoutEnable) {
        setRefreshLayoutEnable(layoutEnable);
        setLoadMoreLayoutEnable(layoutEnable);
    }

    public void setRefreshLayoutEnable(boolean layoutEnable) {
        if(refreshLayout!=null){
            refreshLayout.setEnableRefresh(layoutEnable);
            refreshLayout.setHeaderHeight(layoutEnable?100:0);
            if(topView==null){
                return;
            }
            refreshLayout.setOnMultiPurposeListener(new OnMultiPurposeListener() {
                @Override
                public void onHeaderPulling(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                    if(statusBar.getVisibility()==View.VISIBLE){
                        statusBar.setVisibility(View.INVISIBLE);
                    }
                    if(titleBar.getVisibility()==View.VISIBLE){
                        titleBar.setVisibility(View.INVISIBLE);
                    }
                    if(offset==0){
                        if(statusBar.getVisibility()==View.INVISIBLE) {
                            statusBar.setVisibility(View.VISIBLE);
                        }
                        if(titleBar.getVisibility()==View.INVISIBLE) {
                            titleBar.setVisibility(View.VISIBLE);
                        }
                    }

                }

                @Override
                public void onHeaderReleased(RefreshHeader header, int headerHeight, int extendHeight) {
                    Log.e("extendHeight",extendHeight+" === ");
                }

                @Override
                public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int headerHeight, int extendHeight) {
                    Log.e("extendHeight",extendHeight+" === 1111"+offset);
                    if(offset==0){
                        if(statusBar.getVisibility()==View.INVISIBLE) {
                            statusBar.setVisibility(View.VISIBLE);
                        }
                        if(titleBar.getVisibility()==View.INVISIBLE) {
                            titleBar.setVisibility(View.VISIBLE);
                        }
                    }
                }

                @Override
                public void onHeaderStartAnimator(RefreshHeader header, int headerHeight, int extendHeight) {

                }

                @Override
                public void onHeaderFinish(RefreshHeader header, boolean success) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(statusBar.getVisibility()==View.INVISIBLE) {
                                        statusBar.setVisibility(View.VISIBLE);
                                    }
                                    if(titleBar.getVisibility()==View.INVISIBLE) {
                                        titleBar.setVisibility(View.VISIBLE);
                                    }
                                }
                            });
                        }
                    },600);

                }

                @Override
                public void onFooterPulling(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

                }

                @Override
                public void onFooterReleased(RefreshFooter footer, int footerHeight, int extendHeight) {

                }

                @Override
                public void onFooterReleasing(RefreshFooter footer, float percent, int offset, int footerHeight, int extendHeight) {

                }

                @Override
                public void onFooterStartAnimator(RefreshFooter footer, int footerHeight, int extendHeight) {

                }

                @Override
                public void onFooterFinish(RefreshFooter footer, boolean success) {

                }

                @Override
                public void onLoadmore(RefreshLayout refreshlayout) {

                }

                @Override
                public void onRefresh(RefreshLayout refreshlayout) {

                }

                @Override
                public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

                }
            });
        }
    }

    public void setLoadMoreLayoutEnable(boolean layoutEnable) {
        if(refreshLayout!=null){
            refreshLayout.setEnableLoadmore(layoutEnable);
            refreshLayout.setFooterHeight(layoutEnable?60:0);
        }
    }

    protected View getPaddingTopByView(View view){
        return null;
    }

    protected boolean getTitleBarVisible(){
        return true;
    }

    public abstract int getLayoutId();


    public abstract void initData(@Nullable Bundle savedInstanceState);

    public abstract void initListener();

    /**
     * 沉浸式状态栏
     */
    private void initState() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
