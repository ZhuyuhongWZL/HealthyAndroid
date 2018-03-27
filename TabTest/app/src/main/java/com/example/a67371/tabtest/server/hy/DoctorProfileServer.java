package com.example.a67371.tabtest.server.hy;

import android.util.Log;

import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.controller.hy.ResponseBean;

import java.util.ArrayList;
import java.util.List;

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

public class DoctorProfileServer {

    private static final String TAG = "DoctorProfileServer";

    public interface OnGetDoctors{
        void onGetDoctors(List<ResponseBean.DoctorsBean> list);
    }

    public static void getData(final OnGetDoctors i){
        String url = "http://"+ Content.IP +":8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBean> call = api.getData(0.0);
        call.enqueue(new Callback<ResponseBean>() {
            @Override
            public void onResponse(Call<ResponseBean> call, Response<ResponseBean> response) {
                ResponseBean bean =  response.body();
                    //Log.d(TAG,bean.getDoctors().get(0).getName());
                List<ResponseBean.DoctorsBean> l  = bean.getDoctors();
                i.onGetDoctors(l);
            }

            @Override
            public void onFailure(Call<ResponseBean> call, Throwable t) {
                i.onGetDoctors(new ArrayList<ResponseBean.DoctorsBean>());
            }
        });
    }

    interface API {
        @FormUrlEncoded
        @POST("find_doctor")
        Call<ResponseBean> getData(@Field("star") double star);
    }
}
