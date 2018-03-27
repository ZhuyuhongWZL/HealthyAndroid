package com.mrjk.demo.userdata;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.mrjk.demo.food.ApplyDoctor;
import com.mrjk.demo.food.ApplyDoctorDao;

public class test {
    static Date test = new Date();
    public static void main(String []args){
        UserDataQueryDao userquery = new UserDataQueryDao();
        UserDataUploadDao upload = new UserDataUploadDao();
        com.mrjk.demo.userdata.ApplyDoctorDao applyDoctorDao = new com.mrjk.demo.userdata.ApplyDoctorDao();
        QueryApplystatusDao queryApplystatusDao = new QueryApplystatusDao();
        ApplyAllowDao applyAllowDao = new ApplyAllowDao();
        ApplyRefuseDao applyRefuseDao = new ApplyRefuseDao();
        DoctorLogoutDao doctorLogoutDao = new DoctorLogoutDao();
        //        ApplyDoctorDao applyDoctorDao = new ApplyDoctorDao();
//        Date date = new Date();
//        upload.UpdateUserData("李四",null,233,56,56,13,168,"成都","成都");
        //user.QueryUserData("王奥中",null);
        //applyDoctorDao.applyDoctor("王奥中");
        //applyDoctorDao.HandleApplyAllow("王奥中");
        //applyDoctorDao.HandleApplyRefuse("王奥中");
        //applyDoctorDao.doctorLogout("王奥中");
//        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
//        String nowTime=sf.format(new Date());// new Date()为获取当前系统时间
//        Date date0 = null;
//        String date1 = "2018-03-06";
//        try {
//            date0 = sf.parse(date1);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        java.sql.Date date2 = new java.sql.Date(date0.getTime());
//        System.out.println(date2);

        upload.UpdateUserData("李四",null,0,0,11,25,56,"海口","无锡");
        //userquery.QueryUserData("李四",null);
        //UserData userData = userquery.QueryUserData("王奥中","2018-03-12");
//        applyDoctorDao.applyDoctor("雷锋");
//        ApplyDoctor userData = queryApplystatusDao.QueryStatus("张三");
//       applyAllowDao.HandleApplyAllow("雷锋");
//       applyRefuseDao.HandleApplyRefuse("雷锋");
//       doctorLogoutDao.doctorLogout("雷锋");
//        Gson gson = new Gson();
//        String s = gson.toJson(userData);
//        System.out.println(s);
    }

}
