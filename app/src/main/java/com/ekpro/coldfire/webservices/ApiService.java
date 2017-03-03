package com.ekpro.coldfire.webservices;

import com.ekpro.coldfire.models.StudentDetails;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by dakshal on 05/11/15.
 */
public interface ApiService {

//    @Headers({"Content-Type:application/json", "DeviceType: Android",  "Version: "+ AppConstant.AppVersion, "App: "+ AppConstant.AppName})

//    @GET("/Consumer/appConfig")
//    void getConfig(Callback<ServifyResponse<Config>> configCallback);

    /*
      Temp consumer
     */
//    @POST("/TempConsumer/initialize")
//    void createTempConsumer(@Body TempConsumer tempConsumer, Callback<ServifyResponse<TempConsumer>> tempConsumerCallback);

    @POST("/TempConsumer/initialize")
    Call<ApiResponse<StudentDetails>> requestTempStrudentLogin(@Body HashMap<String, Object> params);

}

