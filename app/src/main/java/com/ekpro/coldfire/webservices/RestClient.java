package com.incredible.computerstudies.webservices;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dakshal on 05/11/15.
 */

public class RestClient {

    public static final RestClient instance = new RestClient();

    private ApiService apiService;

    //Staging
//    public static final String API_BASE_URL = "http://staging.servify.in:5000/api/v1";
    public static final String API_BASE_URL = "http://192.168.43.3:3003/api/";

    //Demo
//    public static final String API_BASE_URL = "http://staging.servify.in:5002/api/v1";

    //Production
//    public static final String API_BASE_URL = "http://production.servify.in:5000/api/v1";
//    public static final String API_BASE_URL = "http://production.servify.in:8080/api/v3";
//    public static final String API_BASE_URL = "https://node.servify.in/consumer/api/v3";

    public RestClient() {

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newBuilder().readTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).connectTimeout(60, TimeUnit.SECONDS).build();

        Retrofit restAdapter = new Retrofit.Builder().client(okHttpClient).
        baseUrl(API_BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
//                .setRequestInterceptor(requestInterceptor)
//                .setEndpoint(API_BASE_URL)
//                .setConverter(new GsonConverter(gson))
//                .setClient(new OkClient(okHttpClient))
//                .build();

        apiService = restAdapter.create(ApiService.class);
    }

//    RequestInterceptor requestInterceptor = new RequestInterceptor() {
//        @Override
//        public void intercept(RequestFacade request) {
//
//            request.addHeader("DeviceType", "Android");
//            request.addHeader("Version", "" + BuildConfig.VERSION_CODE);
//            request.addHeader("App", "Servify");
//        }
//    };

    public ApiService getApiService() {
        return apiService;
    }

}
