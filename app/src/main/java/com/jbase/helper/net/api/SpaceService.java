package com.jbase.helper.net.api;

import com.jbase.helper.net.params.JsonParams;
import com.jbase.helper.test.User;

import java.util.List;

import javax.security.auth.Subject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SpaceService {

  @GET("users/{user}/repos")
  Call<List<User>> listRepos(@Path("user") String user);

  /**
   *  /sq/dyController/MineCircleList.do
   */

//  @HTTP(method = "POST",path = "/sq/dyController/MineCircleList.do",hasBody = true)
  @POST("/sq/dyController/MineCircleList.do")
  Call<ResponseBody> spaceList(@Body JsonParams body);

  // 定义Observable接口类型的网络请求对象
  @POST("/sq/dyController/MineCircleList.do")
  Observable<Subject> spaceList2(@Body JsonParams body);

  // 定义Observable接口类型的网络请求对象
  @POST("/sq/dyController/MineCircleList.do")
  ObservableSource<ResponseBody> spaceList3(@Body JsonParams body);
}