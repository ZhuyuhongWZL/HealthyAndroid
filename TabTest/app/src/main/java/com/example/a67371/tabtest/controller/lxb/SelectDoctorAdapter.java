package com.example.a67371.tabtest.controller.lxb;
import android.content.Context;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.R;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.a67371.tabtest.ui.bean.lxb.ApplyDoctor;

import java.util.ArrayList;
import java.util.List;

public class SelectDoctorAdapter extends BaseAdapter {

    private List<ApplyDoctor.DoctorsBean> list;
    private Context context;

    public SelectDoctorAdapter( Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public List<ApplyDoctor.DoctorsBean> getList() {
        return list;
    }

    public void setList(List<ApplyDoctor.DoctorsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.item_select_doctor,null);
        }
        ImageView imageView = view.findViewById(R.id.img_icon);
        TextView textView = view.findViewById(R.id.tv_name);
        TextView textView1 = view.findViewById(R.id.tv_star);
        ApplyDoctor.DoctorsBean bean = list.get(i);
        String url =  "http://"+ Content.IP +":8080/"+bean.getName();
        Glide.with(context)
                .load(url)
                .into(imageView);
        DecimalFormat df=new DecimalFormat("######0.00");

        textView.setText("用户名:"+bean.getName());
        textView1.setText("星级:"+df.format(bean.getStar()));
        return view;
    }
}