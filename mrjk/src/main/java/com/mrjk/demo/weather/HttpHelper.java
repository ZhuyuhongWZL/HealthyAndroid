package com.mrjk.demo.weather;



import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.http.*;

import java.io.IOException;

public class HttpHelper {
    private static final String BASE_URL = "http://tj.nineton.cn/Heart/index/";


    public interface API {
//        @Headers({
//                "Host: tj.nineton.cn",
//                "Connection: keep-alive",
//                "Upgrade-Insecure-Requests: 1",
//                "User-Agent: Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36",
//                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8",
//                "Accept-Language: zh-CN,zh;q=0.8"
//        })
        @GET("all")
        Call<ResponseBody> getWeather(@Query("city") String townID,
                                      @Query("language") String language,
                                      @Query("unit") String unit,
                                      @Query("aqi") String aqi,
                                      @Query("alarm") String alarm,
                                      @Query("key") String key);
    }

    public static String  getWeather(String townID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getWeather(townID, "zh-chs", "c", "city", "1", "78928e706123c1a8f1766f062bc8676b");
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }
}
