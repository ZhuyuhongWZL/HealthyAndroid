package com.example.a67371.tabtest.ui;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;

public class Http {
    //BASE_URL经常改变，使用的时候问下我的IP是多少，或者使用自己本机的IP，但是本机启动idea下的工程
    private static final String BASE_URL = "http://182.149.197.230:8080/";
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
        Call<ResponseBody> addDoctor(@Field("user") String user, @Field("doctor") String doctor);

        @POST("delete_doctor")
        @FormUrlEncoded
        Call<ResponseBody> deleteDoctor(@Field("user") String user, @Field("doctor") String doctor);

        @POST("isFriend")
        @FormUrlEncoded
        Call<ResponseBody> isFriend(@Field("user") String user, @Field("doctor") String doctor);

        @POST("register")
        @FormUrlEncoded
        Call<ResponseBody> register(@Field("name") String name, @Field("password") String password, @Field("mail") String mail, @Field("repasswd") String repasswd);

        @POST("verify")
        @FormUrlEncoded
        Call<ResponseBody> validate(@Field("verify") String verify,@Field("mail") String mail);

        @POST("login")
        @FormUrlEncoded
        Call<ResponseBody> login(@Field("name") String name,@Field("password") String password);

        @POST("change_user")
        @FormUrlEncoded
        Call<ResponseBody> changeUser(@Field("name") String name,@Field("password") String psaaword,@Field("mail") String mail,@Field("birthday") String birthday,@Field("sex") String sex,@Field("phone") String phone);

        @POST("get_messages")
        @FormUrlEncoded
        Call<ResponseBody> getMessages(@Field("user") String fromUser, @Field("user1") String toUser, @Field("after_time") String afterTime);

        @POST("food")
        @FormUrlEncoded
        Call<ResponseBody> food(@Field("name") String foodName);

        @POST("send_message")
        @FormUrlEncoded
        Call<ResponseBody> sendMessage(@Field("from") String from, @Field("to") String to, @Field("content") String content);

        @POST("critic_doctor")
        @FormUrlEncoded
        Call<ResponseBody> criticDoctor(@Field("star") float star, @Field("username") String userName, @Field("doctorname") String doctorname);

        @POST("user_data/upload")
        @FormUrlEncoded
        Call<ResponseBody> uploadUserData(@Field("name") String name, @Field("date") String date, @Field("walk") int walk, @Field("distance") double distance, @Field("weight") double weight, @Field("height") double height, @Field("calorie") double calorie, @Field("city") String city, @Field("town") String town);

        @POST("user_data")
        @FormUrlEncoded
        Call<ResponseBody> getUserData(@Field("name") String name, @Field("date") String date);

        @POST("food/upload")
        @FormUrlEncoded
        Call<ResponseBody> uploadFood(@Field("name") String name, @Field("alias") String alias, @Field("calorie") int calorie, @Field("uploader") String userName);

        @POST("pic_upload")
        Call<ResponseBody> uploadPic(@Body MultipartBody file, @Field("name") String userName);

        @POST("get_friends")
        @FormUrlEncoded
        Call<ResponseBody> getFriends(@Field("user") String user);

        @POST("get_friends_by_doctor")
        @FormUrlEncoded
        Call<ResponseBody> getFriendsByDoctor(@Field("doctor") String doctor);


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

    public interface OnRegister{
        void OnRegister(String s);
    }

    public interface OnValidate{
        void OnValidate(String s);
    }

    public interface OnLogin{
        void OnLogin(String s);
    }

    public interface OnChangeUser{
        void OnChangeUser(String s);
    }

    public interface OnGetMessages {
        void OnGetMessages(String s);
    }

    public interface OnFood {
        void OnFood(String s);
    }
    public interface OnSendMessage {
        void OnSendMessage(String s);
    }
    public interface OnCriticDoctor {
        void OnCriticDoctor(String s);
    }

    public interface OnUploadUserData {
        void OnUploadUserData(String s);
    }

    public interface OnGetUserData {
        void OnGetUserData(String s);
    }

    public interface OnUploadFood {
        void OnUploadFood(String s);
    }

    public interface OnUploadPic{
        void OnUploadPic(String s);
    }

    public interface OnGetFriends{
        void OnGetFriends(String s);
    }


    public interface OnGetFriendsByDoctor{
        void OnGetFriendsByDoctor(String s);
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

    public static void register(String name, String password, String mail, String repasswd,final OnRegister onRegister){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.register(name,password,mail,repasswd);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onRegister.OnRegister(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void validate(String verify,String mail,final OnValidate onValidate){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.validate(verify,mail);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onValidate.OnValidate(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }


    public static void login(String name,String password,final OnLogin onLogin){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.login(name,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onLogin.OnLogin(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static void changeUser(String name,String password, String mail, String birthday,String sex, String phone,final OnChangeUser onChangeUser){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.changeUser(name,password,mail,birthday,sex,phone);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onChangeUser.OnChangeUser(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static void getMessages(String fromUser, String toUser, String afterTime, final OnGetMessages onGetMessages) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getMessages(fromUser, toUser, afterTime);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onGetMessages.OnGetMessages(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void food(String foodName, final OnFood onFood) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.food(foodName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onFood.OnFood(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void sendMessage(String from, String to, String content, final OnSendMessage onSendMessage) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.sendMessage(from, to, content);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onSendMessage.OnSendMessage(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void criticDoctor(float star, String userName, String doctorName, final OnCriticDoctor onCriticDoctor) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.criticDoctor(star, userName, doctorName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onCriticDoctor.OnCriticDoctor(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void uploadUserData(String name, String date, int walk, double distance, double weight, double height, double calorie, String city, String town, final OnUploadUserData onUploadUserData) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.uploadUserData(name, date, walk, distance, weight, height, calorie, city, town);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onUploadUserData.OnUploadUserData(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void getUserData(String name, String date, final OnGetUserData onGetUserData) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getUserData(name, date);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onGetUserData.OnGetUserData(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void uploadFood(String foodName, String foodAlias, int calorie, String userName, final OnUploadFood onUploadFood) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.uploadFood(foodName, foodAlias, calorie, userName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onUploadFood.OnUploadFood(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static void uploadPic(MultipartBody file,String userName,final OnUploadPic onUploadPic){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.uploadPic(file,userName);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onUploadPic.OnUploadPic(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });





    }

    public static void getFriends(String user,final OnGetFriends onGetFriends){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getFriends(user);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onGetFriends.OnGetFriends(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static void getFriendsByDoctor(String doctor,final OnGetFriendsByDoctor onGetFriendsByDoctor){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        API api = retrofit.create(API.class);
        Call<ResponseBody> call = api.getFriendsByDoctor(doctor);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    onGetFriendsByDoctor.OnGetFriendsByDoctor(response.body().string());

                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }





}
