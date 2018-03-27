package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.controller.dy.UserResponseBean;
import com.google.gson.Gson;

public class UserValidateActivity extends AppCompatActivity {

    private TextView validateTxt;
    private Button validateBtn;
    UserResponseBean userResponseBean1;

    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    Intent it=new Intent(UserValidateActivity.this, LoginActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("userResponse1",userResponseBean1);
                    it.putExtras(bundle);
                    startActivity(it);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.validate_dialog);
        validateTxt = (TextView)findViewById(R.id.et_validate);
        validateBtn = (Button)findViewById(R.id.user_validate);
        final Bundle bundle = this.getIntent().getExtras();


        validateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{



                    final UserResponseBean userResponseBean = (UserResponseBean) bundle.getSerializable("userResponse");
                    final String verify = validateTxt.getText().toString().trim();


                    final String mail = userResponseBean.getUser().getMail();
                    Http.validate(verify, mail, new Http.OnValidate() {
                        @Override
                        public void OnValidate(String s) {
                            Gson gson = new Gson();
                            userResponseBean1 = gson.fromJson(s,UserResponseBean.class);
                            String message = userResponseBean1.getMessage().toString().trim();
                            if(message.equals("请输入正确的验证码")){
                                Toast.makeText(UserValidateActivity.this,"验证码错误",Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(UserValidateActivity.this,"验证成功",Toast.LENGTH_SHORT).show();
                                Message msg = new Message();
                                msg.what = 1;
                                uiHandler.sendMessage(msg);

                            }
                        }
                    });
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });


    }
}
