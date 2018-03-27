package com.example.a67371.tabtest.location;

import java.io.IOException;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hy on 2018/3/11.
 */

public class HttpHelper {
    private static String BASE_URL = "http://api.map.baidu.com/";
    /**
     * 这个AK在不同应用需要多次申请
     * http://lbsyun.baidu.com/apiconsole/key/update?app-id=10908172
     */
    private static String AK = "SGg3G7AGItnA6h15j3BrmTrSPjhzocb1";
    /**
     * 固定值
     */
    private static String CALLBACK = "renderReverse";
    private static String OUTPUT = "json";
    private static String POIS = "0";
    /**
     * 同AK
     */
    private static String MCODE = "64:C6:EF:86:44:04:9A:1E:31:A0:7F:D9:BE:59:5B:46:7A:54:83:B5;com.example.a67371.tabtest";


    /**
     * 耗时任务
     * 返回 ResponseEntity 的json字符串
     * @return
     */
    public static String  getLocation(String location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getLocation(AK,CALLBACK,location,OUTPUT,POIS,MCODE);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        s = s.replace("renderReverse&&renderReverse","");
        s = s.replace("(","");
        s = s.replace(")","");
        return s;
    }

    interface API {
        @GET("geocoder/v2/")
        Call<ResponseBody> getLocation(@Query("ak") String ak,
                                       @Query("callback") String callback,
                                       @Query("location") String location,
                                       @Query("output") String output,
                                       @Query("pois") String pois,
                                       @Query("mcode") String mcode);
    }
}
