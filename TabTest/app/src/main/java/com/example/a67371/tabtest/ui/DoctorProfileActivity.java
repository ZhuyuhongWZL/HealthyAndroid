package com.example.a67371.tabtest.ui;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.controller.hy.DoctorProfileAdapter;
import com.example.a67371.tabtest.controller.hy.ResponseBean;
import com.example.a67371.tabtest.controller.hy.SpacesItemDecoration;
import com.example.a67371.tabtest.server.hy.DoctorProfileServer;
import com.yalantis.euclid.library.EuclidActivity;
import com.yalantis.euclid.library.EuclidListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Oleksii Shliama on 1/27/15.
 */
public class DoctorProfileActivity extends AppCompatActivity {
    private static final String TAG = "DoctorProfileActivity";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private List<ResponseBean.DoctorsBean> list;
    private int index = 0;
    private DoctorProfileAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        swipeRefreshLayout = findViewById(R.id.srl_doctor_profile);
        swipeRefreshLayout.setRefreshing(true);
        recyclerView = findViewById(R.id.rv_doctor_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.chat_bar);
        toolbar.setTitle("医生简介");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        init();
        initData();
    }

    private void initData() {
        DoctorProfileServer.getData(new DoctorProfileServer.OnGetDoctors() {
            @Override
            public void onGetDoctors(List<ResponseBean.DoctorsBean> list) {
                DoctorProfileActivity.this.list = list;
                Log.d(TAG,"---->"+list.size());
                bindData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void init() {
        adapter = new DoctorProfileAdapter(this);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (list == null){
                    DoctorProfileServer.getData(new DoctorProfileServer.OnGetDoctors() {
                        @Override
                        public void onGetDoctors(List<ResponseBean.DoctorsBean> list) {
                            DoctorProfileActivity.this.list = list;
                            Log.d(TAG,"---->"+list.size());
                            bindData();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }else {
                    bindData();
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        //
        recyclerView.setAdapter(adapter);
        //设置分隔线
        recyclerView.addItemDecoration(new SpacesItemDecoration(20));
        //设置增加或删除条目的动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void bindData() {
        if (index>=list.size()) {
            Toast.makeText(this, "没有更多数据了", Toast.LENGTH_SHORT).show();
            return;
        }
        int to = index+10>list.size()?list.size():index+10;
        adapter.setList(list.subList(index,to));
        index += 10;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }


}
