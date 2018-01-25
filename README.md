#一、截图
![](https://github.com/DomerJi/JBase/blob/master/app/pics/Screenshot_2018-01-25-17-34-24-153_com.jbase.help.png)
![](https://github.com/DomerJi/JBase/blob/master/app/pics/Screenshot_2018-01-25-17-35-03-643_com.jbase.help.png)
#二、功能

         /**
         * 自定义TitleBar演示
         */
         @Override
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


            /**
            * 是否沉浸  沉浸返回沉浸的view  默认不沉浸
            */
            @Override
            protected View getPaddingTopByView(View view) {
                return view.findViewById(R.id.topImage);
        //        return null;
            }


            /**
             * 重写是否显示TitleBar  默认显示
             * @return
             */
            @Override
            protected boolean getTitleBarVisible() {
                return true;
            }

