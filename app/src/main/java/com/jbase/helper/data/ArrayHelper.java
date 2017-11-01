package com.jbase.helper.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaa on 2017/8/11.
 */

public class ArrayHelper<T> {

    private List<T> list = new ArrayList<>();

    public List<T> getList(){
        return list;
    }

    public ArrayHelper reset(){
        this.list.clear();
        return this;
    }

    public ArrayHelper load(List<T> list){
        load(list,false);
        return this;
    }

    public ArrayHelper load(List<T> list,boolean reset){
        if(reset){
            this.list.clear();
        }
        if(null != list) {
            this.list.addAll(list);
        }
        return this;
    }

    public int count(List<T> list){
        if(null == list){
            return 0;
        }else {
            return list.size();
        }
    }

}
