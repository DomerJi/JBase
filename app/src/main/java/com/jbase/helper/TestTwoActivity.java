package com.jbase.helper;

import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.jbase.helper.ai.Action;
import com.jbase.helper.ai.Study;
import com.jbase.helper.ai.Thing;
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
import java.util.BitSet;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    private ImageView clipDrawable3;
    private ClipDrawable clipDrawable;

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

        ai();
        structure();

        clipDrawable();

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

    private void ai(){
        Thing thing = new Thing();
        Action action = new Action();
        action.setPlan("吃饭");
        thing.setAction(action);

        Thing thing1 = new Thing();
        Action action1 = new Action();
//        thing1.setEffect(0.1d);
        action1.setPlan("喝水");
        thing1.setAction(action1);

        Study.getInstance().addKnowledge(thing);
        Study.getInstance().addKnowledge(thing1);
        Log.e("AI = ",Study.getInstance().getThings()+"      = = = = "+Study.getInstance().getThings().size());
    }

    private  void structure(){
        BitSet bm = new BitSet();
               System.out.println(bm.isEmpty()+"--"+bm.size());
               bm.set(0);
               System.out.println(bm.isEmpty()+"--"+bm.size());
               bm.set(1);
               System.out.println(bm.isEmpty()+"--"+bm.size());
               System.out.println(bm.get(65));
               System.out.println(bm.isEmpty()+"--"+bm.size());
               bm.set(65);
               System.out.println(bm.isEmpty()+"--"+bm.size());

    }

    private void clipDrawable(){
        Drawable drawable = getResources().getDrawable(R.mipmap.welcome);
        clipDrawable = new ClipDrawable(drawable, Gravity.TOP,ClipDrawable.VERTICAL);
        ImageView clipDrawable3 = (ImageView) findViewById(R.id.clipDrawable);

        clipDrawable3.setImageDrawable(clipDrawable);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0x123);
                if (clipDrawable.getLevel() >= 10000) {
                    timer.cancel();
                }
            }
        },0,50);
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what == 0x123){
                clipDrawable.setLevel(clipDrawable.getLevel()+100);
            }
        }
    };

}
