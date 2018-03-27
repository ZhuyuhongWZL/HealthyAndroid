package com.mrjk.persoanl.mrjk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.leon.lib.settingview.LSettingItem;
import com.squareup.picasso.Picasso;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener{

    private LSettingItem mSettingItemOne;
    private LSettingItem mSettingItemFour;
    private LSettingItem mSettingItemFive;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mSettingItemOne = (LSettingItem) findViewById(R.id.item_one);
        mSettingItemFour = (LSettingItem) findViewById(R.id.item_four);
        mSettingItemFive = (LSettingItem) findViewById(R.id.item_five);

        mSettingItemOne.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemFour.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "选中开关：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
        mSettingItemOne.setRightText("99+");
        mSettingItemFive.setmOnLSettingItemClick(new LSettingItem.OnLSettingItemClick() {
            @Override
            public void click(boolean isChecked) {
                Toast.makeText(getApplicationContext(), "切换开关：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.item_one:
                Toast.makeText(getApplicationContext(), "我的消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
