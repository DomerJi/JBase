package com.jbase.helper.call;

import android.view.View;

/**
 * Created by aaa on 2017/8/11.
 */

public interface OnJBaseListener<T> {

    void onCall(View view,int position,T item);

}
