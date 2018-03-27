package com.example.a67371.tabtest.server.waz;

import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.controller.hy.ResponseBean;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class DoctorApplyServer {

    private static final String TAG = "DoctorApplyServer";

    interface DoctorApplyAPI {
        @FormUrlEncoded
        @POST("apply_doctor")
        Call<ResponseBean> applyDoctor(@Field("name") String name, @Field("introduction") String introduction);
    }


    public static String applyDoctor(String name,String introduction){
        String url = "http://"+ "118.113.10.231"+":8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DoctorApplyAPI api = retrofit.create(DoctorApplyAPI.class);
        Call<ResponseBean> call = api.applyDoctor(name,introduction);
        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                //4.处理结果
                System.out.print("申请成功");
//                ResponseBean bean = response.body();
//                bean.setSuccess(true);
//                bean.setMessage("申请成功");
            }
            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                System.out.print("申请失败");
            }
        });
        return "nihai";
    }

}
