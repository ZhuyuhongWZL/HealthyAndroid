package com.example.a67371.tabtest.server.waz;


import java.io.File;
import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;


public class ApplyDoctorServer {
        private static final String BASE_URL = "http://182.149.197.230:8080/";

        private static final String TAG = "DoctorApplyServer";

        interface DoctorApplyAPI {

            @POST("apply_doctor")
            @FormUrlEncoded
            Call<ResponseBody> applyDoctor(@Field("name") String name, @Field("introduction") String introduction);

            @FormUrlEncoded
            @POST("doctor_upload")
            Call<ResponseBody>doctorUpload(@Field("introduction") String introduction,
                                        @Field("name") String name,
                                        @Field("MultipartFile") File file);

        }

        public interface OnApplyDoctor{
            void OnApplyDoctor(String s);
        }

        public interface OnDoctorUpload {
            void OnDoctorUpload(String s);
        }



        public static void applyDoctor(String name,String introduction,final OnApplyDoctor applyDoctor){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .build();
            DoctorApplyAPI api = retrofit.create(DoctorApplyAPI.class);
            Call<ResponseBody> call = api.applyDoctor(name,introduction);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    //4.处理结果
                    try {
                        applyDoctor.OnApplyDoctor(response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
//                    System.out.print("申请成功");
//                ResponseBean bean = response.body();
//                bean.setSuccess(true);
//                bean.setMessage("申请成功");
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    System.out.print("申请失败");
                }
            });
        }

    public static void doctorUpload(String introduction, String userName, File file, final OnDoctorUpload onDoctorUpload){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        DoctorApplyAPI api = retrofit.create(DoctorApplyAPI.class);
        Call<ResponseBody> call = api.doctorUpload(introduction, userName, file);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    onDoctorUpload.OnDoctorUpload(response.body().string());

                }catch (IOException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
//    public static void uploadPic(String introduction,String userName,File file, final OnUploadPic uploadPic){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .build();
//        DoctorApplyAPI api = retrofit.create(DoctorApplyAPI.class);
//        Call<ResponseBody> call = api.uploadPic(introduction,userName,file);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                //4.处理结果
//                try {
//                    uploadPic.onUploadPic(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
////                    System.out.print("申请成功");
////                ResponseBean bean = response.body();
////                bean.setSuccess(true);
////                bean.setMessage("申请成功");
//            }
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                System.out.print("申请失败");
//            }
//        });
//    }
}
