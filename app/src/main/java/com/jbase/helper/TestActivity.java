package com.jbase.helper;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jbase.helper.base.BaseActivity;
import com.jbase.helper.net.ConnectionListener;
import com.jbase.helper.test.User;
import com.jbase.helper.user.IUser;
import com.jbase.helper.user.UserLoginListener;
import com.jbase.helper.user.UserUtil;
import com.jbase.helper.utils.ImageLoaderUtil;
import com.jbase.helper.view.back.SwipeBackLayout;

import java.util.Arrays;
import java.util.List;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

import static com.jbase.helper.R.id.bg;

public class TestActivity extends BaseActivity implements ConnectionListener,UserLoginListener ,BGASwipeBackHelper.Delegate{

    User testUser = new User("TestActivity");
    private String imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502970220885&di=79d62c1d169b097223e6d71c6987464c&imgtype=0&src=http%3A%2F%2Fimg2.selfimg.com.cn%2FGQgalleryLowerrightWatermarkB%2F2016%2F08%2F02%2F1470111037_q7b3Dd.jpg";
    private ImageView imageView;
    private Button button;
    private BGASwipeBackHelper mSwipeBackHelper;


    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {


//        final View testView = findViewById(R.id.activity_test);
        /**
         * 启用刷新
         *
         */
        setRefreshLayoutEnable(false);
        initSwipeBackFinish();

        SwipeBackLayout mSwipeBackLayout = (SwipeBackLayout) findViewById(R.id.swipe_layout);
        mSwipeBackLayout.setDragDirectMode(SwipeBackLayout.DragDirectMode.VERTICAL);
        mSwipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                mRootView.getBackground().setAlpha(255 - (int) Math.ceil(255 * fractionAnchor));
                mRootView.getBackground().setAlpha(255);
            }
        });

        imageView = (ImageView) findViewById(bg);
        button = (Button) findViewById(R.id.buttonAlpha);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this,"TOAST",Toast.LENGTH_SHORT).show();
            }
        });
        ViewPager pager = (ViewPager) findViewById(R.id.vp);
        final List<TestFragment> list = Arrays.asList(TestFragment.newInstance("left"),TestFragment.newInstance("center"),TestFragment.newInstance("right"));

        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0){
                    Log.e("positionOffset",positionOffset+" -  0");
                    button.setAlpha(positionOffset);
                }else if(position==1){
                    Log.e("positionOffset",positionOffset+" -  1");
                    button.setAlpha(1f-positionOffset);
                }
            }

            @Override
            public void onPageSelected(int position) {
                if(position==1){
                    button.setEnabled(true);
                    button.setAlpha(1f);
                    Glide.with(TestActivity.this).load(imagePath).into(imageView);

                    int brightness = 0; //RGB偏移量，变暗为负数
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
                    ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(matrix);
                    imageView.setColorFilter(cmcf); //imageView为显示图片的View。
                }else {
                    button.setEnabled(false);
                    button.setAlpha(0f);
                    ImageLoaderUtil.LoadBlurImage(activity,imagePath,imageView);

                    int brightness = -80; //RGB偏移量，变暗为负数
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.set(new float[]{1, 0, 0, 0, brightness, 0, 1, 0, 0, brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0});
                    ColorMatrixColorFilter cmcf = new ColorMatrixColorFilter(matrix);
                    imageView.setColorFilter(cmcf); //imageView为显示图片的View。
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        pager.setCurrentItem(1);

        JBase.getInstance().init(this);
        JBase.getInstance().addConnectionListener(this);
        JBase.getInstance().addUserLoginListener(this);

        //是否登录
        if(UserUtil.isLogin()){
            User user = (User) UserUtil.getCurrentUser();
            Log.e("Login","当前登录用户 ："+user.username);
            //模拟更新信息
            user.username = user.username+" : update";



        }else {
            //没有登录时候选择最后一个用户自动登陆
            User user = (User) UserUtil.getLastLoginUser();
            if(null!=user){
                UserUtil.login(user);
                //从未登录选择新用户登录
            }else {
                UserUtil.login(new User("冀帅朋"));
                UserUtil.isLogin();
            }
        }
        if(UserUtil.isLogin()){
            User user = (User) UserUtil.getCurrentUser();
            Log.e("Login","当前登录用户 ："+user.username);
            //模拟更新信息
            user.username = user.username+" : update";

            if(UserUtil.updateIUser(user, UserMessageType.APPNUMBER)){
                User user1 = (User) UserUtil.getCurrentUser();
                Log.e("Login","信息更新成功 ："+user1.username);
            }else {
                Log.e("Login","信息更新失败!");
            }

        }


        //模拟切换用户登录
        UserUtil.login(new User("张三"));

        //模拟退出登录
        UserUtil.loginout();
    }

    @Override
    public void initListener() {

    }

    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);
        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackManager.getInstance().init(this) 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。
        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(false);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.e("Test",testUser.username+" - - - ");

        findViewById(R.id.toTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TestActivity.this,TestTwoActivity.class).putExtra("data",testUser));
            }
        });
    }

    @Override
    public void onConnected() {
        Log.e("TAG","onConnected()");
    }

    @Override
    public void onDisconnected(int var1) {
        Log.e("TAG","onDisconnected()");
    }

    @Override
    public void onNetTypeChanged(Type var1) {
        Log.e("TAG","onNetTypeChanged()"+(var1==Type.WIFI?"WIFI":var1==Type.NO?"NO":"GPRS"));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        JBase.getInstance().removeConnectionListener(this);
        JBase.getInstance().removeUserLoginListener(this);
    }

    @Override
    public void onLogin(IUser user) {
        Log.e("TAG","onLogin()"+user.getUserId());
    }

    @Override
    public void onUserChanged(IUser oldUser, IUser newUser) {
        Log.e("TAG","onUserChanged()"+oldUser.getUserId()+" to "+newUser.getUserId());
    }

    @Override
    public void onUserMessageChanged(IUser newUser, UserMessageType type) {

    }

    @Override
    public void onBackLogin(IUser user) {
        Log.e("TAG","onBackLogin()"+user.getUserId());
    }

    @Override
    protected boolean getTitleBarVisible() {
        return true;
    }

    @Override
    protected View getPaddingTopByView(View view) {
//        return view.findViewById(R.id.activity_test);
        return null;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {

    }

    @Override
    public void onSwipeBackLayoutCancel() {

    }

    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }
}
