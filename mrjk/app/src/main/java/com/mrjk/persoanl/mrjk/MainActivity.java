package com.mrjk.persoanl.mrjk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    private TextView txtView;
    private RelativeLayout relativeLayout;
    private TextView settingTxtView;
    private TextView userInfoTxtView;
    private TextView userDoctorTxtView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtView = (TextView)findViewById(R.id.user_weight);
        relativeLayout = (RelativeLayout)findViewById(R.id.view_user);
        settingTxtView = (TextView)findViewById(R.id.user_setting);
        userInfoTxtView = findViewById(R.id.user_info);
        userDoctorTxtView = findViewById(R.id.user_doctor);
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, weight_record.class);
                startActivity(intent);
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent1);
            }
        });

        settingTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,SettingActivity.class);
                startActivity(intent2);
            }
        });
        userInfoTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten3 = new Intent(MainActivity.this,EditUserInfoActivity.class);
                startActivity(inten3);
            }
        });


        userDoctorTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this,DoctorProfileActivity.class);
                startActivity(intent4);
            }
        });



    }
}
