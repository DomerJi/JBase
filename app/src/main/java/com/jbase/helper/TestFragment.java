package com.jbase.helper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aaa on 2017/8/17.
 */

public class TestFragment extends Fragment {

    public static TestFragment newInstance(String title){
        TestFragment testFragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putCharSequence("title",title);
        testFragment.setArguments(bundle);
        return testFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test,null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String title = getArguments().getString("title","title");
        if(title.equals("center")) {
            TextView textView = (TextView) getView().findViewById(R.id.mytext);
            textView.setVisibility(View.GONE);
        }
    }
}
