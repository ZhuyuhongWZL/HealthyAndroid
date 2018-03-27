package com.example.a67371.tabtest.controller.hy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a67371.tabtest.R;

import java.util.ArrayList;
import java.util.List;

public class DoctorProfileAdapter extends RecyclerView.Adapter<DoctorProfileItemViewHolder>{
    private Context context;
    private List<ResponseBean.DoctorsBean> list;

    public DoctorProfileAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>(10);
    }

    public void clearData(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addData(List<ResponseBean.DoctorsBean> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void addData(ResponseBean.DoctorsBean bean){
        this.list.add(bean);
        notifyDataSetChanged();
    }

    public List<ResponseBean.DoctorsBean> getList() {
        return list;
    }

    public void setList(List<ResponseBean.DoctorsBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public DoctorProfileItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_doctor_profile,null);
        DoctorProfileItemViewHolder viewHolder = new DoctorProfileItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorProfileItemViewHolder holder, final int position) {
        holder.bindData(list.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "hello-->"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
