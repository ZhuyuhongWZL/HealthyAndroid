package com.example.a67371.tabtest.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.controller.lxb.SelectDoctorAdapter;
import com.example.a67371.tabtest.server.lxb.Http;
import com.example.a67371.tabtest.ui.bean.lxb.ApplyDoctor;
import com.example.a67371.tabtest.ui.bean.lxb.UserEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelectDoctorActivity extends Activity {
    private ListView mListView;
    private TextView mTextView;

    private ImageView imageView;
    private ApplyDoctor applyDoctor=new ApplyDoctor();

    UserEntity userEntity=new UserEntity();

    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    Intent it=new Intent(SelectDoctorActivity.this, MakeFriendsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("user", userEntity);
                    it.putExtras(bundle);
                    startActivity(it);
                    break;
            }
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_doctor);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        final Bundle bundle = this.getIntent().getExtras();

        mListView=(ListView) findViewById(R.id.listview);
        imageView=(ImageView) findViewById(R.id.img_icon);

        applyDoctor=(ApplyDoctor)bundle.getSerializable("applyDoctors");
        if(applyDoctor.getDoctors().size()==0){
            mTextView=(TextView)findViewById(R.id.textview);
            mTextView.setText("没有查到这类医生- -");
        }
        else{

            SelectDoctorAdapter selectDoctorAdapter=new SelectDoctorAdapter(this);
            selectDoctorAdapter.setList(applyDoctor.getDoctors());
            mListView.setAdapter(selectDoctorAdapter);

            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    mTextView=(TextView)view.findViewById(R.id.tv_name);
                    String userName=applyDoctor.getDoctors().get(i).getName();
                    Http.getUserByName(userName, new Http.OnGetUser() {
                        @Override
                        public void onGetUser(String s) {
                            Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
                            List<UserEntity> list = gson.fromJson(s, new TypeToken<List<UserEntity>>() {}.getType());
                            userEntity=list.get(0);
                            System.out.println(s);
//                            Toast.makeText(SelectDoctorActivity.this,s , Toast.LENGTH_SHORT).show();
                            Message msg = new Message();
                            msg.what = 1;
                            uiHandler.sendMessage(msg);
                        }
                    });
                }
            });

        }


    }

}