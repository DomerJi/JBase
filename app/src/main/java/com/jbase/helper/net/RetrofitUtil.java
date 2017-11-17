package com.jbase.helper.net;

import com.google.gson.Gson;
import com.jbase.helper.call.OnByTypeListener;
import com.jbase.helper.net.api.Ip;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aaa on 2017/11/16.
 */

public class RetrofitUtil {

    static Converter.Factory factory = GsonConverterFactory.create();
    static Gson gson = new Gson();
    static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Ip.BASE_URL)
            .addConverterFactory(factory)
            .build();

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public static void enqueue( Call<ResponseBody> call, final OnByTypeListener onByTypeListener){

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String content = response.body().string();
                    Logger.json(content);
                    if(null!=onByTypeListener){
                        onByTypeListener.onSucess(gson.fromJson(content,onByTypeListener.getTypeClass()));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });


    }

}
