package com.jbase.helper;

import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jbase.helper.net.ConnectionListener;
import com.jbase.helper.test.User;
import com.jbase.helper.user.IUser;
import com.jbase.helper.user.UserLoginListener;
import com.jbase.helper.user.UserUtil;

import java.util.Arrays;
import java.util.List;

import static com.jbase.helper.R.id.bg;

public class TestActivity extends AppCompatActivity implements ConnectionListener,UserLoginListener {

    User testUser = new User("TestActivity");
    private String imagePath = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1502970220885&di=79d62c1d169b097223e6d71c6987464c&imgtype=0&src=http%3A%2F%2Fimg2.selfimg.com.cn%2FGQgalleryLowerrightWatermarkB%2F2016%2F08%2F02%2F1470111037_q7b3Dd.jpg";
    private ImageView imageView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

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
                    Glide.with(TestActivity.this).load(imagePath).transform(new GlideBlurTransform(TestActivity.this)).into(imageView);

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

}
