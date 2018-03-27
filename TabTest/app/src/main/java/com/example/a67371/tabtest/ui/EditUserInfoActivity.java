package com.example.a67371.tabtest.ui;

import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.controller.dy.UserResponseBean;
import com.example.a67371.tabtest.security.DataFormat;
import com.example.a67371.tabtest.ui.calendar.CalendarDay;
import com.example.a67371.tabtest.ui.calendar.MaterialCalendarView;
import com.example.a67371.tabtest.ui.calendar.OnDateSelectedListener;
import com.example.a67371.tabtest.ui.dialog.DateDialog;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class EditUserInfoActivity extends AppCompatActivity {
    private TextView nameTxt,sexTxt,mailTxt,birthTxt,phoneTxt,passwdTxt,saveTxt;
    UserResponseBean userResponseBean;
    private CircleImageView circleImageView;


    String neckname;
    private String[] sexArry = new String[]{"女", "男"};// 性别选择

    DateDialog dialog;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    private String selectDate;

    OnDateSelectedListener listener = new OnDateSelectedListener() {
        @Override
        public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
            selectDate=simpleDateFormat.format(date.getDate());
            birthTxt.setText(df.format(date.getDate()));
            dialog.cancel();
        }
    };

    public static MultipartBody filesToMultipartBody(List<File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();

        for (File file : files) {
            // TODO: 16-4-2  这里为了简单起见，没有判断file的类型
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/png"), file);
            builder.addFormDataPart("file", file.getName(), requestBody);
        }

        builder.setType(MultipartBody.FORM);
        MultipartBody multipartBody = builder.build();
        return multipartBody;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_info);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_bar);
        toolbar.setTitle("个人资料");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String name =  UserData.getName();
        String mail = UserData.getMail();
        String birthday = UserData.getBirthday();
        String phone = UserData.getPhone();
        String sex = UserData.getSex();

        nameTxt = (TextView) findViewById(R.id.user_name);
        sexTxt = (TextView)findViewById(R.id.user_sex);
        mailTxt = (TextView)findViewById(R.id.user_mail);
        birthTxt = (TextView)findViewById(R.id.user_birthday);
        phoneTxt = (TextView)findViewById(R.id.user_phone);
        passwdTxt = (TextView)findViewById(R.id.user_passwd);
        saveTxt = (TextView)findViewById(R.id.save_info);
        circleImageView = (CircleImageView)findViewById(R.id.head);

        nameTxt.setText(name);
        mailTxt.setText(mail);
        if (sex != null) {
            sexTxt.setText(sex);
        }else{
            sexTxt.setText("");
        }

        if (birthday != null) {
            birthTxt.setText(birthday);
        }else{
            birthTxt.setText("");
        }

        if (phone != null) {
            phoneTxt.setText(phone);
        }else{
            phoneTxt.setText("");
        }

        circleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

        nameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateNameDialog();
            }
        });

        sexTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSexChooseDialog();
            }
        });

        mailTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreateMailDialog();
            }
        });

        phoneTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreatePhoneDialog();
            }
        });

        passwdTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCreatePasswdDialog();
            }
        });

        birthTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DateDialog(EditUserInfoActivity.this, R.layout.date_dialog, listener);
                dialog.show();
            }
        });

        saveTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_name = nameTxt.getText().toString().trim();
                String new_sex = sexTxt.getText().toString().trim();
                String new_mail = mailTxt.getText().toString().trim();
                String new_birthday = birthTxt.getText().toString().trim();
                String new_phone = phoneTxt.getText().toString().trim();
                String new_passwd = passwdTxt.getText().toString().trim();
                if (new_passwd.equals("")){
                    new_passwd = UserData.getPassword();
                }

                if (DataFormat.isName(new_name) && DataFormat.isSex(new_sex) && DataFormat.isEmail(new_mail) && DataFormat.isBirthday(new_birthday) && DataFormat.isPassword(new_passwd) &&DataFormat.isPhone(new_phone)) {
                    try {
                        Http.changeUser(new_name, new_passwd, new_mail, new_birthday, new_sex, new_phone, new Http.OnChangeUser() {
                            @Override
                            public void OnChangeUser(String s) {

                            }
                        });

                        UserData.setName(new_name);
                        UserData.setSex(new_sex);
                        UserData.setMail(new_mail);
                        UserData.setBirthday(new_birthday);
                        UserData.setPhone(new_phone);
                        UserData.setPassword(new_passwd);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }else{
                    Toast.makeText(EditUserInfoActivity.this,"数据格式错误",Toast.LENGTH_LONG);
                }


            }
        });
    }




    private void showSexChooseDialog() {
        AlertDialog.Builder builder3 = new AlertDialog.Builder(this);// 自定义对话框
        builder3.setSingleChoiceItems(sexArry, 0, new DialogInterface.OnClickListener() {// 2默认的选中

            @Override
            public void onClick(DialogInterface dialog, int which) {// which是被选中的位置
                // showToast(which+"");
                sexTxt.setText(sexArry[which]);
                dialog.dismiss();// 随便点击一个item消失对话框，不用点击确认取消
            }
        });
        builder3.show();// 让弹出框显示
    }

    private void onCreateMailDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View mailView = layoutInflater.inflate(R.layout.mail_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(mailView);

        final EditText mailInput = (EditText) mailView.findViewById(R.id.et_change_mail);



        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                mailTxt.setText(mailInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void onCreateNameDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View nameView = layoutInflater.inflate(R.layout.name_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(nameView);

        final EditText nameInput = (EditText) nameView.findViewById(R.id.et_change_name);



        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                nameTxt.setText(nameInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void onCreatePhoneDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View phoneView = layoutInflater.inflate(R.layout.phone_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(phoneView);

        final EditText phoneInput = (EditText) phoneView.findViewById(R.id.et_change_phone);



        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                phoneTxt.setText(phoneInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    private void onCreatePasswdDialog() {
        // 使用LayoutInflater来加载dialog_setname.xml布局
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View phoneView = layoutInflater.inflate(R.layout.passwd_dialog, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);

        // 使用setView()方法将布局显示到dialog
        alertDialogBuilder.setView(phoneView);

        final EditText passwdInput = (EditText) phoneView.findViewById(R.id.et_change_passwd);



        // 设置Dialog按钮
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 获取edittext的内容,显示到textview
                                passwdTxt.setText(passwdInput.getText());
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
