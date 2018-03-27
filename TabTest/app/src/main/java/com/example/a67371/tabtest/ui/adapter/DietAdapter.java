package com.example.a67371.tabtest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.ui.bean.Diet;

import java.util.List;

/**
 * Created by wenjie on 2018/3/13.
 */

public class DietAdapter extends ArrayAdapter<Diet> {
    private int resourceId;

    public DietAdapter(Context context, int textViewResourceId,
                       List<Diet> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Diet diet = getItem(position); // 获取当前项的 Chater 实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent,
                false);
        ImageView dietImage = (ImageView) view.findViewById(R.id.food_image);
        TextView dietName = (TextView) view.findViewById(R.id.food_name);
        TextView dietNumber = (TextView) view.findViewById(R.id.food_number);
        TextView dietKarori = (TextView) view.findViewById(R.id.food_karori);
        dietImage.setImageResource(diet.getImageId());
        dietName.setText(diet.getFoodName());
        dietNumber.setText(String.valueOf(diet.getNumber()));
        dietKarori.setText(String.valueOf(diet.getKarori())+"千卡");
        return view;

    }
}
