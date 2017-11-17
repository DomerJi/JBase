package com.jbase.helper.net.params;

import android.text.TextUtils;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by aaa on 2017/11/15.
 */

public class JsonParams extends HashMap<String, Object> implements Serializable {

    public JsonParams addParams(String key, Object value) {
        if (value instanceof String) {
            if (!TextUtils.isEmpty((String) value)) {
                put(key, value);
            }
        } else if(value instanceof CharSequence) {
            if (!TextUtils.isEmpty((CharSequence) value)) {
                put(key, value);
            }
        } else if (value instanceof Integer) {
            if ((Integer) value > -1) {
                put(key, value);
            }
        } else {
            put(key, value);
        }
        return this;
    }

    public String toJson(){
        return new JSONObject(this).toString();
    }

    public String getParams(String key){
        Object object = get(key);
        if(object!=null){
            return String.valueOf(object);
        }else {
            return "";
        }
    }

}
