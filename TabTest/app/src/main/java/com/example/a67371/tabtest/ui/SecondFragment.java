package com.example.a67371.tabtest.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.controller.dy.UserResponseBean;

import org.w3c.dom.Text;

/**
 * Created by 67371 on 2018/3/7.
 */

public class SecondFragment extends android.support.v4.app.Fragment {
    private TextView txtView;
    private RelativeLayout relativeLayout;
    private TextView settingTxtView;
    private TextView userInfoTxtView;
    private TextView userDoctorTxtView;
    private TextView userFoodTxtView;
    private TextView usernameTxtView;
    private TextView usermsgTxtView;
    private TextView doctorProfileTxtView;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        txtView = (TextView) view.findViewById(R.id.user_weight);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.view_user);
        settingTxtView = (TextView) view.findViewById(R.id.user_setting);
        userInfoTxtView = view.findViewById(R.id.user_info);
        userDoctorTxtView = view.findViewById(R.id.user_doctor);
        userFoodTxtView = view.findViewById(R.id.user_food);
        usernameTxtView = view.findViewById(R.id.user_name);
        usermsgTxtView = view.findViewById(R.id.user_msg);
        doctorProfileTxtView = view.findViewById(R.id.doctor_profile);
        usernameTxtView.setText(UserData.getName());
        usermsgTxtView.setText("账号：" + UserData.getMail());


        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), WeightRecord.class);
                startActivity(intent);
            }
        });

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (usernameTxtView.getText().toString().trim().equals("菲儿")) {
                    Intent intent1 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent1);
                }else{
                    Intent intent = new Intent(getActivity(),EditUserInfoActivity.class);
                    startActivity(intent);
                }
            }
        });

        settingTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent2);
            }
        });
        userInfoTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent inten3 = new Intent(getActivity(), EditUserInfoActivity.class);

                startActivity(inten3);
            }
        });


        userDoctorTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity(), DoctorApplyActivity.class);
                startActivity(intent4);
            }
        });

        userFoodTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity(), DietActivity.class);
                startActivity(intent5);
            }
        });


        doctorProfileTxtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),DoctorProfileActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}

