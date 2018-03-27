package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.zkk.view.rulerview.RulerView;

public class WeightInputActivity extends AppCompatActivity {

    private RulerView ruler_height;   //身高的view
    private RulerView ruler_weight ;  //体重的view
    private TextView tv_register_info_height_value,tv_register_info_weight_value;
    double weight ;
    double height ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_input_);
        ruler_height=(RulerView)findViewById(R.id.ruler_height);
        ruler_weight=(RulerView)findViewById(R.id.ruler_weight);

        tv_register_info_height_value=(TextView) findViewById(R.id.tv_register_info_height_value);
        tv_register_info_weight_value=(TextView) findViewById(R.id.tv_register_info_weight_value);

        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_bar);
        toolbar.setTitle("身高体重");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ruler_weight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_weight_value.setText(value+"");
                weight = Double.valueOf(tv_register_info_weight_value.getText().toString().trim());

            }
        });

        ruler_height.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_height_value.setText(value+"");
                height = Double.valueOf(tv_register_info_height_value.getText().toString().trim());
            }
        });



/**
 *
 * @param selectorValue 未选择时 默认的值 滑动后表示当前中间指针正在指着的值
 * @param minValue   最大数值
 * @param maxValue   最小的数值
 * @param per   最小单位  如 1:表示 每 2 条刻度差为 1. 0.1:表示 每 2 条刻度差为 0.1 在 demo 中 身高 mPerValue 为 1  体重 mPerValue 为 0.1
 */

        ruler_height.setValue(165, 80, 250, 1);

        ruler_weight.setValue(165, 30, 250, 1);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.yes:

                Intent intent = new Intent();
                intent.putExtra("weight",weight);
                intent.putExtra("height",height);
                setResult(RESULT_OK, intent);
                finish();
                break;
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weight_input_menu, menu);//加载menu布局
        return true;
    }

}