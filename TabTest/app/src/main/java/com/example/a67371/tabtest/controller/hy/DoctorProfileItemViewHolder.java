package com.example.a67371.tabtest.controller.hy;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.R;

public class DoctorProfileItemViewHolder extends RecyclerView.ViewHolder {
    private View rootView;
    private TextView tvName;
    private TextView tvIntroduction;
    private ImageView ivUser;

    public DoctorProfileItemViewHolder(View rootView) {
        super(rootView);
        this.rootView = rootView;
        tvName = rootView.findViewById(R.id.tv_name);
        ivUser = rootView.findViewById(R.id.iv_doctor);
        tvIntroduction = rootView.findViewById(R.id.tv_introduction);
    }

    public void setOnClickListener(View.OnClickListener l){
        rootView.setOnClickListener(l);
    }

    public void bindData(ResponseBean.DoctorsBean bean) {
        if (bean == null)return;
        tvName.setText(bean.getName());
        tvIntroduction.setText(bean.getIntroduction());
        String url = "http://"+ Content.IP+":8080/"+bean.getName();
        Context context = rootView.getContext();
        Glide.with(context)
                .load(url)
                .placeholder(R.drawable.profile)
                .transform(new GlideCircleTransform(context))
                .error(R.drawable.profile)
                .into(ivUser);
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvIntroduction() {
        return tvIntroduction;
    }

    public void setTvIntroduction(TextView tvIntroduction) {
        this.tvIntroduction = tvIntroduction;
    }
}
