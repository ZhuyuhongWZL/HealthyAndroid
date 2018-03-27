package com.mrjk.persoanl.mrjk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.zkk.view.rulerview.*;
import android.widget.*;

public class Weight_input_Activity extends AppCompatActivity {

    private RulerView ruler_height;   //身高的view
    private RulerView ruler_weight ;  //体重的view
    private TextView tv_register_info_height_value,tv_register_info_weight_value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_input_);
        ruler_height=(RulerView)findViewById(R.id.ruler_height);
        ruler_weight=(RulerView)findViewById(R.id.ruler_weight);

        tv_register_info_height_value=(TextView) findViewById(R.id.tv_register_info_height_value);
        tv_register_info_weight_value=(TextView) findViewById(R.id.tv_register_info_weight_value);

        ruler_weight.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_weight_value.setText(value+"");
            }
        });

        ruler_height.setOnValueChangeListener(new RulerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                tv_register_info_height_value.setText(value+"");
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

}