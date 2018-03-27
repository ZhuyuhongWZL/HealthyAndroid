package com.example.a67371.tabtest.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.server.waz.ApplyDoctor;
import com.example.a67371.tabtest.server.waz.ApplyDoctorServer;
import com.example.a67371.tabtest.server.waz.UserEntity;
import com.example.a67371.tabtest.server.waz.FileUtils;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.google.gson.Gson;

import java.io.File;
import java.security.PrivateKey;

import okhttp3.MultipartBody;

public class DoctorApplyActivity extends AppCompatActivity {
    private EditText introduction;
    private TextView fileaddress;
    private Button select;
    private Button submit;
    private Button reset;
    private String name;
    private String mImageFileName;
    private File tempFile;
    private static final int PHOTO_REQUEST_GALLERY = 2;
    private static final int PHOTO_REQUEST_CUT=3;
    private int i=0;

    private Uri uri;
    TextView text ;
    Button button;
    private ImageView iv_image1;
    private File file;
    //private Multipartfile multipartFile;
    private Bitmap bitmap;


    private ApplyDoctor apply = new ApplyDoctor();
    private static final int TAKE_PICTURE = 0x000001;



    private Handler uiHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    Intent it = new Intent(DoctorApplyActivity.this, DoctorApplyActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("applyDoctor", apply);
                    Toast.makeText(DoctorApplyActivity.this,"上传成功",Toast.LENGTH_SHORT).show();
                    it.putExtras(bundle);
                    startActivity(it);

                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doctor);
        Toolbar toolbar = (Toolbar)findViewById(R.id.pic_toolbar);
        toolbar.setTitle("从医资质申请");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        name = UserData.getName();
        initView();


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Bitmap getBitmapFromUri(Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }



    public void gallery(View view) {
        // 激活系统图库，选择一张图片
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_GALLERY
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);

    }

    private void crop(Uri uri) {
        // 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", true);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == PHOTO_REQUEST_GALLERY){
            if(data!=null){
                uri = data.getData();
                crop(uri);
                System.out.println(uri);

            }
        }else if (requestCode == PHOTO_REQUEST_CUT) {
            // 从剪切图片返回的数据
            if (data != null) {
                Bitmap bitmap = data.getParcelableExtra("data");

                iv_image1.setImageBitmap(bitmap);
                iv_image1.setBackgroundColor(0);

            }
            try {
                // 将临时文件删除
                tempFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(resultCode,resultCode,data);
    }

    private void initView(){
        introduction = (EditText)findViewById(R.id.doctor_apply);
        select = (Button)findViewById(R.id.select);
        submit = (Button)findViewById(R.id.submit);
        reset = (Button)findViewById(R.id.reset);
        iv_image1 = (ImageView) findViewById(R.id.iv_image1);


        introduction.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==0){
                    Toast.makeText(DoctorApplyActivity.this,"请输入您的简介信息",Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (introduction.getText().toString().isEmpty()) {
                    Toast.makeText(DoctorApplyActivity.this, "请输入您的简介信息", Toast.LENGTH_SHORT).show();
                } else {

                        String intro = introduction.getText().toString().trim();


                        ApplyDoctorServer.applyDoctor(name, intro, new ApplyDoctorServer.OnApplyDoctor() {
                            @Override
                            public void OnApplyDoctor(String s) {
                                Gson gson = new Gson();
                                apply = gson.fromJson(s, ApplyDoctor.class);

                                if (apply.isSuccess()) {
                                    Toast.makeText(DoctorApplyActivity.this, "申请成功", Toast.LENGTH_SHORT).show();

                                } else {
                                    Toast.makeText(DoctorApplyActivity.this, "您已经申请过了哦，请勿重复申请！", Toast.LENGTH_SHORT).show();

                                }

                                //System.out.println(introduction);
//                                Message msg = new Message();
//                                msg.what = 1;
//                                uiHandler.sendMessage(msg);
                            }
                        });
//                        ApplyDoctorServer.doctorUpload(intro, name, file, new ApplyDoctorServer.OnDoctorUpload() {
//                            @Override
//                            public void OnDoctorUpload(String s) {
//                                Gson gson = new Gson();
//                                BackBean back = gson.fromJson(s, BackBean.class);
//                                if(back.isSuccess()) {
//                                    Toast.makeText(DoctorApplyActivity.this,"上传成功！", Toast.LENGTH_SHORT).show();
//
//                                    return;
//                                } else {
//                                    Toast.makeText(DoctorApplyActivity.this,"上传失败！", Toast.LENGTH_SHORT).show();
//                                    return;
//                                }
//                            }
//                        });

                    }
                }

        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                introduction.setText("");

                iv_image1.setImageBitmap(null);

                uri = null;
            }
        });

        }

}

