package com.jbase.helper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jbase.helper.test.User;

public class TestTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_two);
        User user = (User) getIntent().getSerializableExtra("data");
        user.username = "TestTwo";
    }
}
