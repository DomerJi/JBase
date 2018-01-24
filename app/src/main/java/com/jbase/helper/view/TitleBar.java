package com.jbase.helper.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.jbase.helper.R;

import java.util.ArrayList;
import java.util.List;

import static android.util.TypedValue.COMPLEX_UNIT_PX;

/**
 * Created by aaa on 2018/1/23.
 */

public class TitleBar extends FrameLayout {
    Builder builder;
    private int  foregroundColor = Color.BLACK;
    private int width;
    private int height;
    private TabClickLstener tabClickLstener;
    private LinearLayout left;
    private LinearLayout right;
    public ColorFilterImageView titleBarBack;

    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addView(LayoutInflater.from(getContext()).inflate(R.layout.titlebar_imp,null));
        left = (LinearLayout) findViewById(R.id.titleBarLeft);
        right = (LinearLayout) findViewById(R.id.titleBarRight);


    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        init();

    }


    private void init(){

        int padding = height/4;
        titleBarBack = (ColorFilterImageView) findViewById(R.id.titleBarBack);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) titleBarBack.getLayoutParams();
        layoutParams.width = height;
        layoutParams.height = height;
        titleBarBack.setLayoutParams(layoutParams);
        titleBarBack.setPadding(padding,padding,padding,padding);
        titleBarBack.setColorFilter(foregroundColor, PorterDuff.Mode.SRC_IN);
        if(builder!=null){
            titleBarBack.setVisibility(builder.backUpEnable?VISIBLE:GONE);
            if(builder.backgroundResId!=-1){
                titleBarBack.setBackgroundResource(builder.backgroundResId==0?R.drawable.base_btn_sel:builder.backgroundResId);
            }

            if(!builder.tabs.isEmpty()){
                for(final TabLabel tab : builder.tabs){
                    if(!TextUtils.isEmpty(tab.name)){
                        PressTextView tabView = new PressTextView(getContext());
                        tabView.setTextColor(foregroundColor);
                        tabView.setText(tab.name);
                        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,height);
                        tabView.setLayoutParams(lP);
                        tabView.setPadding(padding,padding,padding,padding);
                        tabView.setTextSize(COMPLEX_UNIT_PX,height/3);
                        tabView.setBackgroundResource(builder.backgroundResId==0?R.drawable.base_btn_sel:builder.backgroundResId);
                        if(tab.isClick) {
                            tabView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (tabClickLstener != null) {
                                        tabClickLstener.onClick(v, -1, tab.name);
                                    }
                                }
                            });
                        }
                        if(tab.type==Type.Left){
                            left.addView(tabView);
                        }else {
                            right.addView(tabView);
                        }
                    }else if(tab.resId>0){
                        ColorFilterImageView tabView = new ColorFilterImageView(getContext());
                        tabView.setImageResource(tab.resId);
                        LinearLayout.LayoutParams lP = new LinearLayout.LayoutParams(height,height);
                        tabView.setLayoutParams(lP);
                        tabView.setPadding(padding,padding,padding,padding);
                        tabView.setColorFilter(foregroundColor, PorterDuff.Mode.SRC_IN);
                        tabView.setBackgroundResource(builder.backgroundResId==0?R.drawable.base_btn_sel:builder.backgroundResId);
                        if(tab.isClick) {
                            tabView.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (tabClickLstener != null) {
                                        tabClickLstener.onClick(v, tab.resId, null);
                                    }
                                }
                            });
                        }
                        if(tab.type==Type.Left){
                            left.addView(tabView);
                        }else {
                            right.addView(tabView);
                        }
                    }


                }
            }
        }



        titleBarBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
    public Builder Builder(){
        if(builder==null){
            this.builder = new Builder();
        }
        return builder;
    }

    public interface TabClickLstener{
        public void onClick(View view,int id,String title);
    }

    public class TabLabel{
        public Type type = Type.Left;
        public String name;
        public int resId;
        public boolean isClick = true;

        /**
         * 创建一个字符标签
         * @param type 左右
         * @param name 字符标签
         */
        public TabLabel(Type type, String name) {
            this.type = type;
            this.name = name;
        }

        /**
         * 创建一个字符标签
         * @param type 左右
         * @param name 字符标签
         */
        public TabLabel(Type type, String name,boolean isClick) {
            this.type = type;
            this.name = name;
            this.isClick = isClick;
        }

        /**
         * 创建一个字符标签
         * @param name 字符标签
         */
        public TabLabel(String name) {
            this.name = name;
        }

        /**
         * 创建一个字符标签
         * @param name 字符标签
         */
        public TabLabel(String name,boolean isClick) {
            this.name = name;
            this.isClick = isClick;
        }

        /**
         * 创建一个图片标签
         * @param type 左右
         * @param resId 图片资源
         */
        public TabLabel(Type type, int resId) {
            this.type = type;
            this.resId = resId;
        }

        /**
         * 创建一个图片标签
         * @param type 左右
         * @param resId 图片资源
         */
        public TabLabel(Type type, int resId,boolean isClick) {
            this.type = type;
            this.resId = resId;
            this.isClick = isClick;
        }


        /**
         * 创建一个图片标签
         * @param resId 图片资源
         */
        public TabLabel( int resId) {
            this.resId = resId;
        }

        /**
         * 创建一个图片标签
         * @param resId 图片资源
         */
        public TabLabel( int resId,boolean isClick) {
            this.resId = resId;
            this.isClick = isClick;
        }
    }

    public enum Type{
        Left,Right;
    }
    public class Builder{
        private int backgroundResId = -1;
        private boolean backUpEnable = false;
        private List<TabLabel> tabs = new ArrayList<>();

        public Builder setBackUpEnable(boolean enable){
            backUpEnable = enable;
            return this;
        }

        public Builder setBackgroundPressState(int resId){
            backgroundResId = resId;
            return this;
        }

        public Builder setBackgroundPressState(){
            setBackgroundPressState(0);
            return this;
        }

        public Builder addTabString(String name){
            if(!TextUtils.isEmpty(name)) {
                tabs.add(new TabLabel(name));
            }
            return this;
        }

        public Builder addTabString(String name,boolean isClick){
            if(!TextUtils.isEmpty(name)) {
                tabs.add(new TabLabel(name,isClick));
            }
            return this;
        }

        public Builder addTabString(String name,Type type){
            if(!TextUtils.isEmpty(name)) {
                if(type!=Type.Right){
                    tabs.add(new TabLabel(name));
                }else {
                    tabs.add(new TabLabel(type,name));
                }

            }
            return this;
        }

        public Builder addTabString(String name,Type type,boolean isClick){
            if(!TextUtils.isEmpty(name)) {
                if(type!=Type.Right){
                    tabs.add(new TabLabel(name,isClick));
                }else {
                    tabs.add(new TabLabel(type,name,isClick));
                }

            }
            return this;
        }

        public Builder addTabImage(int resId,Type type){
            if(resId>0) {
                if(type!=Type.Right){
                    tabs.add(new TabLabel(resId));
                }else {
                    tabs.add(new TabLabel(type,resId));
                }
            }
            return this;
        }

        public Builder addTabImage(int resId,Type type,boolean isClick){
            if(resId>0) {
                if(type!=Type.Right){
                    tabs.add(new TabLabel(resId,isClick));
                }else {
                    tabs.add(new TabLabel(type,resId,isClick));
                }
            }
            return this;
        }

        public Builder addTabImage(int resId){
            if(resId>0) {
                tabs.add(new TabLabel(resId));
            }
            return this;
        }

        public Builder addTabImage(int resId,boolean isClick){
            if(resId>0) {
                tabs.add(new TabLabel(resId,isClick));
            }
            return this;
        }

        public Builder setForegroundColor(int color){
            TitleBar.this.foregroundColor = color;
            return this;
        }

        public Builder setOnTabClickListener(TabClickLstener tabClickListener){
            TitleBar.this.tabClickLstener = tabClickListener;
            return this;
        }

        public void create(){
            init();
        }
    }

}
