package com.example.a67371.tabtest.server.lxb;


import com.example.a67371.tabtest.Content;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class Http {
    private static final String BASE_URL = "http://"+ Content.IP +":8080/";

    public interface API {
        @POST("get_user_by_name")
        @FormUrlEncoded
        Call<ResponseBody> getUserByName(@Field("name") String name);



        @POST("find_doctor")
        @FormUrlEncoded
        Call<ResponseBody> findDoctor(@Field("star") double star);

        @POST("find_doctor_by_name")
        @FormUrlEncoded
        Call<ResponseBody> findDoctorByName(@Field("name") String name);

        @POST("add_doctor")
        @FormUrlEncoded
        Call<ResponseBody> addDoctor(@Field("user") String user,@Field("doctor") String doctor);

        @POST("delete_doctor")
        @FormUrlEncoded
        Call<ResponseBody> deleteDoctor(@Field("user") String user,@Field("doctor") String doctor);

        @POST("isFriend")
        @FormUrlEncoded
        Call<ResponseBody> isFriend(@Field("user") String user,@Field("doctor") String doctor);
    }



    public interface OnGetDoctorByName{
        void onGetDoctorByName(String s);
    }

    public interface OnGetDoctor{
        void onGetDoctor(String s);
    }
    public interface OnGetUser{
        void onGetUser(String s);
    }

    public interface OnAddDoctor{
        void onAddDoctor(String s);
    }

    public interface OnDeleteDoctor{
        void onDeleteDoctor(String s);
    }
    public interface OnIsFriend{
        void onIsFriend(String s);
    }
    public static void  getUserByName(String name ,final OnGetUser getUser){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getUserByName(name);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getUser.onGetUser(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public static void  findDoctor(double star, final OnGetDoctor getDoctor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.findDoctor(star);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getDoctor.onGetDoctor(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public static void  findDoctorByName(String name, final OnGetDoctorByName getDoctorByName) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.findDoctorByName(name);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    getDoctorByName.onGetDoctorByName(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }

    public static void  addDoctor(String user,String doctor, final OnAddDoctor onAddDoctor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.addDoctor(user,doctor);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onAddDoctor.onAddDoctor(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
    public static void  deleteDoctor(String user,String doctor, final OnDeleteDoctor onDeleteDoctor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.deleteDoctor(user,doctor);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onDeleteDoctor.onDeleteDoctor(response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
    public static void  isFriend(String user,String doctor, final OnIsFriend onIsFriend) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.isFriend(user,doctor);

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onIsFriend.onIsFriend(response.body().string());
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
