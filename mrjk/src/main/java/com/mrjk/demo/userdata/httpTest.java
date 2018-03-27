package com.mrjk.demo.userdata;

import com.google.gson.Gson;
import com.mrjk.demo.food.ApplyDoctor;

public class httpTest {
    public static void main(String []args){
        Gson gson =new Gson();
        String s="";
//
////        s=UserdataHttp.queryState("王奥中");
////        ApplyDoctor state = gson.fromJson(s,ApplyDoctor.class);
////        System.out.println(s);
//
////        s=UserdataHttp.applyDoctor("王麻子");
////        ApplyDoctor apply = gson.fromJson(s,ApplyDoctor.class);
////        System.out.println(s);
//
////        s=UserdataHttp.queryUserData("王奥中","2018-03-12");
////        UserData userData = gson.fromJson(s,UserData.class);
////        System.out.println(s);
//
        s=UserdataHttp.updateUserData("王奥中","2018-03-26",13,25,25,12,15,"湖南","长沙");
        UserData userData1 = gson.fromJson(s,UserData.class);
        System.out.println(s);
    }
}
