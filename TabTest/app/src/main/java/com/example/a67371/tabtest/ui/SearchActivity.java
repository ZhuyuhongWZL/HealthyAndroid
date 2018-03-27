package com.example.a67371.tabtest.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.server.lxb.Http;
import com.example.a67371.tabtest.ui.adapter.MyListViewCursorAdapter;
import com.example.a67371.tabtest.ui.bean.lxb.ApplyDoctor;
import com.example.a67371.tabtest.ui.db.MyOpenHelper;
import com.google.gson.Gson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchActivity extends Activity {

    private EditText mEditText;
    private ImageView mImageView;
    private ListView mListView;
    private TextView mTextView;

    private ApplyDoctor applyDoctor=new ApplyDoctor();
    private Handler uiHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:

                    Intent it=new Intent(SearchActivity.this, SelectDoctorActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("applyDoctors", applyDoctor);
                    it.putExtras(bundle);
                    startActivity(it);

                    break;
            }
        }
    };



    private Switch mSwitch;
    private boolean switchState=false;
    Context context;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        context = this;
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        initView();

    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textview);
        mEditText = (EditText) findViewById(R.id.edittext);
        mImageView = (ImageView) findViewById(R.id.imageview);
        mListView = (ListView) findViewById(R.id.listview);

        mSwitch=(Switch)findViewById(R.id.searchdoctorswitch);

        mSwitch.setChecked(false);
        //开关变化
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    mSwitch.setText("按照姓名查找");
                    switchState=true;
                }else {
                    mSwitch.setText("按照星级查找");
                    switchState=false;
                }
            }
        });


        //设置删除图片的点击事件
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //把EditText内容设置为空
                mEditText.setText("");
                //把ListView隐藏
                mListView.setVisibility(View.GONE);
            }
        });




        //EditText添加监听
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            //文本改变之前执行
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            //文本改变的时候执行
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //如果长度为0
                if (s.length() == 0) {
                    //隐藏“删除”图片
                    mImageView.setVisibility(View.GONE);
                } else {//长度不为0
                    //显示“删除图片”
                    mImageView.setVisibility(View.VISIBLE);
                    //显示ListView
                    showListView();
                }
            }

            //文本改变之后执行
            public void afterTextChanged(Editable s) {
            }
        });



        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //如果输入框内容为空，提示请输入搜索内容
                if(TextUtils.isEmpty(mEditText.getText().toString().trim())){
                    Toast.makeText(SearchActivity.this,"您输入的内容为空",Toast.LENGTH_SHORT).show();
                }else {


                    if(switchState){
                        String name =mEditText.getText().toString().trim();
                        Http.findDoctorByName(name, new Http.OnGetDoctorByName() {
                            @Override
                            public void onGetDoctorByName(String s) {
                                Gson gson =new Gson();
                                applyDoctor = gson.fromJson(s, ApplyDoctor.class);
                                System.out.println(s);
                                Message msg = new Message();
                                msg.what = 1;
                                uiHandler.sendMessage(msg);
                            }
                        });


                    }
                    else{
                        String starString=mEditText.getText().toString().trim();
                        Pattern p = Pattern.compile("[1-9]\\d*.\\d*|0.\\d*[0-9]\\d*|[0-9]\\d*");
                        Pattern p1 = Pattern.compile("\\+([1-9]\\d*.\\d*|0.\\d*[0-9]\\d*|[0-9]\\d*)");
                        Matcher m = p.matcher(starString);
                        Matcher m1 = p1.matcher(starString);
                        if(m.matches() || m1.matches()){

                            Double star =Double.parseDouble(mEditText.getText().toString().trim()) ;
                            //      测试寻找星级不低于参数的医生
                            if(star>5.0){
                                Toast.makeText(SearchActivity.this,"请填写0-5之间的正数",Toast.LENGTH_SHORT).show();
                            }else{
                                Http.findDoctor(star, new Http.OnGetDoctor() {
                                    @Override
                                    public void onGetDoctor(String s) {
                                        Gson gson =new Gson();
                                        applyDoctor = gson.fromJson(s, ApplyDoctor.class);
                                        System.out.println(s);
                                        Message msg = new Message();
                                        msg.what = 1;
                                        uiHandler.sendMessage(msg);
                                    }
                                });
                            }

                        }else{
                            Toast.makeText(SearchActivity.this,"请填写0-5之间的正数",Toast.LENGTH_SHORT).show();
                        }


                    }
                    //判断cursor是否为空
//                    if (cursor != null) {
//                        int columnCount = cursor.getCount();
//                        if (columnCount == 0) {
//                            Toast.makeText(SearchActivity.this,"没有您要搜索的内容",Toast.LENGTH_SHORT).show();
//                        }
//                    }
                }

            }
        });


    }






    private void showListView() {
        mListView.setVisibility(View.VISIBLE);
        //获得输入的内容
        String str = mEditText.getText().toString().trim();
        //获取数据库对象
        MyOpenHelper myOpenHelper = new MyOpenHelper(getApplicationContext());
        SQLiteDatabase db = myOpenHelper.getReadableDatabase();
        //得到cursor
        cursor = db.rawQuery("select * from lol where name like '%" + str + "%'", null);
        MyListViewCursorAdapter adapter = new MyListViewCursorAdapter(context, cursor);

        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //把cursor移动到指定行
                cursor.moveToPosition(position);
                String name = cursor.getString(cursor.getColumnIndex("name"));
                Toast.makeText(SearchActivity.this,name,Toast.LENGTH_SHORT).show();
            }
        });
    }
}