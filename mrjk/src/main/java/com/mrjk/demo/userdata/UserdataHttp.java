package com.mrjk.demo.userdata;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import java.io.IOException;

public class UserdataHttp {
    private static final String BASE_URL = "http://localhost:8080/";
    public interface UserdataAPI {
        @POST("user_data")
        @FormUrlEncoded
        Call<ResponseBody> QueryUserData(@Field("name") String name, @Field("date") String date);

        @POST("user_data/upload")
        @FormUrlEncoded
        Call<ResponseBody> UpdateUserData(@Field("name") String name, @Field("date") String date, @Field("walk") int walk, @Field("distance") double distance, @Field("weight") double weight, @Field("height") double height, @Field("calorie") double calorie, @Field("city") String city, @Field("town") String town);

        @POST("apply_doctor")
        @FormUrlEncoded
        Call<ResponseBody> applyDoctor(@Field("name") String name, @Field("introduction") String introduction);

        @POST("apply_doctor/state")
        @FormUrlEncoded
        Call<ResponseBody> QueryStatus(@Field("name") String name);

        @POST("apply_doctor/out")
        @FormUrlEncoded
        Call<ResponseBody> doctorLogout(@Field("name") String name);

        @POST("apply_doctor/handleApplyAllow")
        @FormUrlEncoded
        Call<ResponseBody> HandleApplyAllow(@Field("name") String name);

        @POST("apply_doctor/handleApplyRefuse")
        @FormUrlEncoded
        Call<ResponseBody>  HandleApplyRefuse(@Field("name") String name);
    }

    public static String updateUserData(String name, String date,int walk, double distance, double weight, double height, double calorie, String city, String town){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.UpdateUserData(name,date,walk,distance,weight,height,calorie,city,town);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String queryUserData(String name, String date){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.QueryUserData(name,date);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String applyDoctor(String name,String introduction){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.applyDoctor(name,introduction);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String queryState(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.QueryStatus(name);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String doctorLogout(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.doctorLogout(name);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String allowApply(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.HandleApplyAllow(name);
        String s = "";
        try {
            Response<ResponseBody> body = call.execute();
            s = body.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static String refuseApply(String name){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        UserdataAPI userdataAPI = retrofit.create(UserdataAPI.class);
        Call<ResponseBody> call = userdataAPI.HandleApplyRefuse(name);
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
