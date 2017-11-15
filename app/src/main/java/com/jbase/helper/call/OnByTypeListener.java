package com.jbase.helper.call;

import java.lang.reflect.Type;

/**
 * Created by aaa on 2017/11/14.
 */

public abstract class OnByTypeListener<T> implements CallBack {

    public abstract void onSucess(T bean);

    public void onFailed(String error){
    }

    /**
     *    return new TypeToken<T>() {}.getType();
     * @return
     */
    public abstract Type getTypeClass();

}
