package com.jbase.helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jbase.helper.call.OnByTypeListener;
import com.jbase.helper.test.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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

    }

    public <T> void toprintf(T... gs){

    }
}
