package com.mrjk.demo.platform.xjtu;

import com.mrjk.demo.platform.Dao.MessageDao;
/*import com.example.demo.activity.UserActivity;*/
import com.mrjk.demo.platform.bean.TalkMessageEntity;
/*import com.example.demo.server.ServerListen;*/
import com.google.gson.Gson;

import java.util.List;

public class user {
    public static void main(String[] args) {
        MessageDao messageDao= new MessageDao();
       TalkMessageEntity message = messageDao.sendMessage("123456","12345");
        System.out.println(message);
        Gson gson =new Gson();
        String s = gson.toJson(message);
    }
}
