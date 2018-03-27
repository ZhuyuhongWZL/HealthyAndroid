package com.example.a67371.tabtest.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.ui.bean.AddFood;

import java.util.List;

/**
 * Created by wenjie on 2018/3/13.
 */

public class AddFoodAdapter extends BaseAdapter {
    private List<AddFood> list;
    private LayoutInflater inflater;

    public AddFoodAdapter(Context context,List<AddFood> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public AddFood getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public void updateView(List<AddFood> list) {
        this.list = list;
        this.notifyDataSetChanged();//强制动态刷新数据进而调用getView方法
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AddFood food = getItem(position); // 获取当前项的 Chater 实例
        ViewHolder holder = null;
        View view = null;
        holder = new ViewHolder();
        if(convertView == null)
        {
            view = inflater.inflate(R.layout.add_food_list, parent, false);
            holder = new ViewHolder();
            holder.addFoodImage = (ImageView) view.findViewById(R.id.add_food_image);
            holder.addFoodName = (TextView) view.findViewById(R.id.add_food_name);
            holder.addFoodKarori = (TextView) view.findViewById(R.id.add_food_eachkarori);
            holder.addFoodNumber = (TextView) view.findViewById(R.id.add_food_number);
            view.setTag(holder);//为了复用holder
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.addFoodImage.setImageResource(food.getImageId());
        holder.addFoodName.setText(food.getFoodName());
        holder.addFoodKarori.setText(String.valueOf(food.getEachKarori())+"千卡/每份");
        if(food.getFoodNumber()==0) holder.addFoodNumber.setText("");
        else holder.addFoodNumber.setText("已选"+String.valueOf(food.getFoodNumber())+"份");
        return view;

    }

    static class ViewHolder
    {
        ImageView addFoodImage;
        TextView addFoodName;
        TextView addFoodKarori;
        TextView addFoodNumber;
    }
}
