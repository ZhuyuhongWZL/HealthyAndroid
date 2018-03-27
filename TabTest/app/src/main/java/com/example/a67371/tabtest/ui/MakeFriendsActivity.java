package com.example.a67371.tabtest.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.server.lxb.Http;
import com.example.a67371.tabtest.ui.bean.lxb.UserEntity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class MakeFriendsActivity extends AppCompatActivity {

    private TextView userName;
    private TextView userSex;
    private TextView userBrithday;
    private TextView userPhone;
    private TextView userMail;
    private TextView mTextView;


    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mTextView=(TextView) findViewById(R.id.make_friends);
            switch (msg.what){
                case 1:

                    mTextView.setText("删除好友");

                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_friends);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        final Bundle bundle = this.getIntent().getExtras();
        UserEntity userEntity1=(UserEntity)bundle.getSerializable("user");

        CircleImageView imageView=(CircleImageView)findViewById(R.id.head);
        String url =  "http://"+ Content.IP +":8080/"+userEntity1.getName();
        Glide.with(this)
                .load(url)
                .into(imageView);

        userName = (TextView) findViewById(R.id.user_name);
        userSex = (TextView) findViewById(R.id.user_sex);
        userBrithday = (TextView) findViewById(R.id.user_birthday);
        userPhone = (TextView) findViewById(R.id.user_phone);
        userMail = (TextView) findViewById(R.id.user_mail);
        mTextView=(TextView) findViewById(R.id.make_friends);

        final String username=userEntity1.getName();
        String usersex=userEntity1.getSex();
        String userbrithday;
        if(userEntity1.getBirthday()!=null){
            userbrithday=userEntity1.getBirthday()+"";
        }else userbrithday="";


        String userphone=userEntity1.getPhone();
        String usermail=userEntity1.getMail();

        userName.setText("昵称: "+username);
        userSex.setText("性别: "+usersex);
        userBrithday.setText("生日: "+userbrithday);
        userPhone.setText("电话: "+userphone);
        userMail.setText("邮箱: "+usermail);


        Http.isFriend(UserData.getName(), username, new Http.OnIsFriend() {
            @Override
            public void onIsFriend(String s) {

                Message msg = new Message();
                if(s.equals("true"))
                    msg.what = 1;

                uiHandler.sendMessage(msg);
            }
        });

        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String statu=mTextView.getText().toString().trim();
                if(statu.equals("加为好友")){
                    Http.addDoctor(UserData.getName(), username, new Http.OnAddDoctor() {
                        @Override
                        public void onAddDoctor(String s) {

                        }
                    });
                    mTextView.setText("删除好友");
                }
                else{

                    Http.deleteDoctor(UserData.getName(), username, new Http.OnDeleteDoctor() {
                        @Override
                        public void onDeleteDoctor(String s) {

                        }
                    });
                    mTextView.setText("加为好友");
                }
            }
        });

    }
}
