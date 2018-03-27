package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.ui.bean.BackBean;
import com.example.a67371.tabtest.ui.bean.UserDataBean;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeightRecord extends AppCompatActivity {
    private CardView cardView;
    private String userName = UserData.getName();
    double weight,height;

    TextView txtYourWeight;
    TextView txtYourHeight;

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_record);
        cardView = (CardView)findViewById(R.id.weight_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_bar);
        toolbar.setTitle("身高体重");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtYourWeight = (TextView) findViewById(R.id.txtYourWeight);
        txtYourHeight = (TextView) findViewById(R.id.txtYourHeight);
        Http.getUserData(userName, format.format(date), new Http.OnGetUserData() {
            @Override
            public void OnGetUserData(String s) {
                Gson gson = new Gson();
                UserDataBean userDataBean = gson.fromJson(s, UserDataBean.class);
                if(userDataBean.isSuccess()) {
                    txtYourHeight.setText(String.valueOf(userDataBean.getData().getHeight()));
                    txtYourWeight.setText(String.valueOf(userDataBean.getData().getWeight()));
                } else Toast.makeText(WeightRecord.this,"获取用户信息失败！", Toast.LENGTH_SHORT).show();
            }
        });
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WeightRecord.this,WeightInputActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    weight = data.getDoubleExtra("weight", 0f);
                    height = data.getDoubleExtra("height",0f);
                    Http.uploadUserData(userName, format.format(date), 0, 0f, weight, height, 0f, "", "", new Http.OnUploadUserData() {
                        @Override
                        public void OnUploadUserData(String s) {
                            Gson gson = new Gson();
                            BackBean back = gson.fromJson(s, BackBean.class);
                            if(back.isSuccess()) {
                                Toast.makeText(WeightRecord.this,"上传成功！", Toast.LENGTH_SHORT).show();
                                txtYourWeight.setText(String.valueOf((int)weight));
                                txtYourHeight.setText(String.valueOf((int)height));
                            } else Toast.makeText(WeightRecord.this,"上传失败！", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                break;
            default:
        }

    }
}
