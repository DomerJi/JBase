package com.jbase.helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jbase.helper.call.OnByTypeListener;
import com.jbase.helper.net.ActionUserInfoBean;
import com.jbase.helper.net.RetrofitUtil;
import com.jbase.helper.net.api.Ip;
import com.jbase.helper.net.api.SpaceService;
import com.jbase.helper.net.params.JsonParams;
import com.jbase.helper.test.User;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.Subject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);
        User user = (User) getIntent().getSerializableExtra("data");
        user.username = "TestTwo";

        final List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        String json =  new Gson().toJson(new User("JI"));
        OnByTypeListener byTypeListener = new OnByTypeListener<User>() {
            @Override
            public void onSucess(User bean) {
                Log.e("ByTypeTest",bean.username);
            }

            @Override
            public Type getTypeClass() {
                return User.class;
            }
        };

        byTypeListener.onSucess(new Gson().fromJson(json,byTypeListener.getTypeClass()));

        String json1 =  new Gson().toJson(users);
        OnByTypeListener onByTypeListener = new OnByTypeListener<List<User>>() {

            @Override
            public void onSucess(List<User> bean) {
                Log.e("ByTypeTest",bean.size()+" = ");
            }

            @Override
            public Type getTypeClass() {
                return new TypeToken<List<User>>(){}.getType();
            }
        };

        onByTypeListener.onSucess(new Gson().fromJson(json1,onByTypeListener.getTypeClass()));

        Converter.Factory factory = GsonConverterFactory.create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Ip.BASE_URL)
                .addConverterFactory(factory)
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter());
        SpaceService service = retrofit.create(SpaceService.class);
        JsonParams body = new JsonParams().addParams("account", "17085375010")
                .addParams("page", 1)
                .addParams("pagesize", 20)
                .addParams("type", 1);

        service.spaceList(body).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String content = response.body().string();
                    Log.e("Tag",content);
                    Logger.json(content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

        RetrofitUtil.enqueue(RetrofitUtil.getRetrofit().create(SpaceService.class).spaceList(body), new OnByTypeListener<ActionUserInfoBean>() {
            @Override
            public void onSucess(ActionUserInfoBean bean) {
                Logger.i("RetrofitUtil = "+bean.getInfoMsg());
            }

            @Override
            public Type getTypeClass() {
                return ActionUserInfoBean.class;
            }
        });

//        toprintf();



    }

    public void toprintf(){

        // 步骤1：创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://fy.iciba.com/") // 设置 网络请求 Url
                .addConverterFactory(GsonConverterFactory.create()) //设置使用Gson解析(记得加入依赖)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava
                .build();

        // 步骤2：创建 网络请求接口 的实例
        SpaceService request = retrofit.create(SpaceService.class);

        // 步骤3：采用Observable<...>形式 对 2个网络请求 进行封装
        JsonParams body = new JsonParams().addParams("account", "17085375010")
                .addParams("page", 1)
                .addParams("pagesize", 20)
                .addParams("type", 1);
        Observable<Subject> observable1 = request.spaceList2(body);

        final ObservableSource<ResponseBody> observable2 = request.spaceList3(body);

    }
}
