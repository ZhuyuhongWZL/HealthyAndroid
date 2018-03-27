package com.mrjk.demo.userdata;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

public class UserDataQueryDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    List<UserDataEntity> f;

    //查询用户某天数据 date为要查询的日期
    public UserData QueryUserData(String name,String date){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        UserDataEntity userDataEntity = null;
        UserData userData = new UserData();

        String result = "";
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowTime = sf.format(new Date());// new Date()为获取当前系统时间;
        java.sql.Date date2 = null;
        Date date1 = null;
        if(date==null){
            try {
                date1 = sf.parse(nowTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date2 = new java.sql.Date(date1.getTime());
        }else{
            try {
                date1 = sf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            date2 = new java.sql.Date(date1.getTime());
        }
        f = session.createQuery("from UserDataEntity  F where F.name = '"+name+"' AND F.date = '"+date2+"'").list();
        session.close();
        sessionFactory.close();
        if (f.isEmpty()){
            userData.setSuccess(false);
            userData.setMessage("查询失败");
            result = "该日暂无数据";
            System.out.println(result);
            return userData;
        }
        else {
            userData.setSuccess(true);
            userData.setMessage("查询成功");
            UserData.DataBean dataBean = new UserData.DataBean();
            dataBean.setWalk(f.get(0).getWalk());
            dataBean.setDistance(f.get(0).getDistance());
            dataBean.setWeight(f.get(0).getWeight());
            dataBean.setHeight(f.get(0).getHeight());
            dataBean.setCalorie(f.get(0).getCalorie());
            dataBean.setLocationCity(f.get(0).getLocationCity());
            dataBean.setLocationTown(f.get(0).getLocationTown());
            userData.setData(dataBean);
            result = "您当日的步数为"+f.get(0).getWalk()+"行走距离为："+f.get(0).getDistance()+"体重为："+f.get(0).getWeight()+"身高为："+f.get(0).getHeight()+"摄入的卡洛里为："+f.get(0).getCalorie()+"您所在的城市为："+f.get(0).getLocationCity()+" "+f.get(0).getLocationTown();
            System.out.println(result);
            return userData;
        }
    }
    public int GetWalk(){
        int walk = f.get(0).getWalk();
        return walk;
    }
    public double GetDistance(){
        double distance = f.get(0).getDistance();
        return distance;
    }
    public double GetWeight(){
        double weight = f.get(0).getWeight();
        return weight;
    }
    public double GetHeight(){
        double height = f.get(0).getHeight();
        return height;
    }
    public double GetCalorie(){
        double calorie = f.get(0).getCalorie();
        return calorie;
    }
    public String GetCity(){
        String city = f.get(0).getLocationCity();
        return city;
    }
    public String GetTown(){
        String town = f.get(0).getLocationTown();
        return town;
    }
}
