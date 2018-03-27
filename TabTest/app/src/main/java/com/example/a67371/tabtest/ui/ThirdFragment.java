package com.example.a67371.tabtest.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.bumptech.glide.Glide;
import com.example.a67371.tabtest.Content;
import com.example.a67371.tabtest.R;
import com.example.a67371.tabtest.UserData;
import com.example.a67371.tabtest.controller.dy.Friends;
import com.example.a67371.tabtest.ui.bean.lxb.ApplyDoctor;
import com.example.a67371.tabtest.ui.refresh.RefreshableView;
import com.example.a67371.tabtest.ui.refresh.ThirdRefreshableView;
import com.google.gson.Gson;


import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by 67371 on 2018/3/8.
 */

public class ThirdFragment extends Fragment {
//    private Context mContext;
    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;
    private ApplyDoctor applyDoctor;
    private Friends friendresponse;

    ThirdRefreshableView refreshableView;

   /* private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                     = (String) msg.obj;

            }
        }
    }; */




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.third_fragment,container,false);

        final Context context=getActivity();
//        mContext = context;

        exlist_lol = (ExpandableListView) view.findViewById(R.id.exlist_lol);

        ArrayList<Group> gData = new ArrayList<Group>();
        final ArrayList<ArrayList<Item>> iData = new ArrayList<ArrayList<Item>>();
        final ArrayList<Item> lData = new ArrayList<Item>();
//        final ArrayList<Item> lData1 = new ArrayList<>();
        gData.add(new Group("我的好友"));
//        gData.add(new Group("未读消息"));


        myAdapter = new MyBaseExpandableListAdapter(gData, iData,context);
        exlist_lol.setAdapter(myAdapter);
        iData.add(lData);
//        iData.add(lData1);
        try{
            Http.getFriends(UserData.getName(), new Http.OnGetFriends() {
                @Override
                public void OnGetFriends(String s) {
                    System.out.println(s);
                    s = "{\"friends\":" + s + "}";
                    System.out.println(s);
                    Gson g = new Gson();
                    Friends friends = g.fromJson(s, Friends.class);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = s;
                   // mHandler.sendMessage(msg);
//                    friends.
//                    handler.post(searchFriends);
                    List<Friends.Friend> l = friends.getFriends();
                    for (Friends.Friend f :l){
                        Item i = new Item();
                        if (! f.getDoctor().equals(UserData.getName())) {
                            i.setiName(f.getDoctor());
                            lData.add(i);

                        }
                    }


                    myAdapter.notifyDataSetChanged();


                }
            });


        }catch(Exception e){
            e.printStackTrace();
        }


        try{
            Http.getFriendsByDoctor(UserData.getName(), new Http.OnGetFriendsByDoctor() {
                @Override
                public void OnGetFriendsByDoctor(String s) {
                    System.out.println(s);
                    s = "{\"friends\":" + s + "}";
                    System.out.println(s);
                    Gson g = new Gson();
                    Friends friends = g.fromJson(s, Friends.class);
//                    friends.
//                    handler.post(searchFriends);
                    List<Friends.Friend> l = friends.getFriends();
                    for (Friends.Friend f :l){
                        Item i = new Item();
                        if ( ! f.getUser().equals(UserData.getName())) {
                            i.setiName(f.getUser());
                            lData.add(i);

                        }
                    }


                    myAdapter.notifyDataSetChanged();

                }
            });
        }catch (Exception e){
            e.printStackTrace();
        }














        //数据准备





        //AP组

        //AD组

//        lData.add(new Item(R.mipmap.iv_lol_icon3,"剑圣"));
//        lData.add(new Item(R.mipmap.iv_lol_icon4,"德莱文"));
//        lData.add(new Item(R.mipmap.iv_lol_icon13,"男枪"));
//        lData.add(new Item(R.mipmap.iv_lol_icon14,"韦鲁斯"));
        refreshableView = (ThirdRefreshableView) view.findViewById(R.id.refreshable_view);
        refreshableView.setOnRefreshListener(new ThirdRefreshableView.PullToRefreshListener() {
            @Override
            public void onRefresh() {
                try{
                    Http.getFriends(UserData.getName(), new Http.OnGetFriends() {
                        @Override
                        public void OnGetFriends(String s) {
                            System.out.println(s);
                            s = "{\"friends\":" + s + "}";
                            System.out.println(s);
                            Gson g = new Gson();
                            Friends friends = g.fromJson(s, Friends.class);
                            Message msg = new Message();
                            msg.what = 1;
                            msg.obj = s;
                            // mHandler.sendMessage(msg);
//                    friends.
//                    handler.post(searchFriends);
                            List<Friends.Friend> l = friends.getFriends();
                            lData.clear();
                            for (Friends.Friend f :l){
                                Item i = new Item();
                                if (! f.getDoctor().equals(UserData.getName())) {
                                    i.setiName(f.getDoctor());
                                    lData.add(i);

                                }
                            }


                            myAdapter.notifyDataSetChanged();


                        }
                    });


                }catch(Exception e){
                    e.printStackTrace();
                }


                try{
                    Http.getFriendsByDoctor(UserData.getName(), new Http.OnGetFriendsByDoctor() {
                        @Override
                        public void OnGetFriendsByDoctor(String s) {
                            System.out.println(s);
                            s = "{\"friends\":" + s + "}";
                            System.out.println(s);
                            Gson g = new Gson();
                            Friends friends = g.fromJson(s, Friends.class);
//                    friends.
//                    handler.post(searchFriends);
                            List<Friends.Friend> l = friends.getFriends();
                            for (Friends.Friend f :l){
                                Item i = new Item();
                                if ( ! f.getUser().equals(UserData.getName())) {
                                    i.setiName(f.getUser());
                                    lData.add(i);

                                }
                            }


                            myAdapter.notifyDataSetChanged();

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }
                refreshableView.finishRefreshing();
            }
        }, 2);

//        ExpandableListView expandableListView=(ExpandableListView)view.findViewById(R.id.exlist_lol) ;
        exlist_lol.expandGroup(0);


        //为列表设置点击事件
        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                intent.putExtra("chat_name",iData.get(groupPosition).get(childPosition).getiName());
                startActivity(intent);
                return true;
            }
        });


        //悬浮按钮
        FloatingActionButton add=(FloatingActionButton)view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}

