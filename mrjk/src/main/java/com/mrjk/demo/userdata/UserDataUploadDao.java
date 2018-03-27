package com.mrjk.demo.userdata;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserDataUploadDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    List<UserDataEntity> f;
    Serializable ser;

    //上传用户当天数据
    public UserData UpdateUserData(String name, String date,int walk,double distance,double weight, double height,double calorie,String city,String town) {
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        UserData userdata = new UserData();
        UserDataEntity data = new UserDataEntity();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        String nowTime = sf.format(new Date());// new Date()为获取当前系统时间
        java.sql.Date date2 = null;
        Date date1 = null;
        if(("".equals(date))|| (date == null)){
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
        if (f.isEmpty()){
            data.setName(name);
            data.setDate(date2);
            data.setWalk(walk);
            data.setDistance(distance);
            data.setWeight(weight);
            data.setHeight(height);
            data.setCalorie(calorie);
            data.setLocationCity(city);
            data.setLocationTown(town);
//            UserData.DataBean dataBean = new UserData.DataBean();
//            dataBean.setWalk(walk);
//            dataBean.setDistance(distance);
//            dataBean.setWeight(weight);
//            dataBean.setHeight(height);
//            dataBean.setCalorie(calorie);
//            dataBean.setLocationCity(city);
//            dataBean.setLocationTown(town);
//            userdata.setData(dataBean);
            ser = session.save(data);
        }
        else {
            f.get(0).setName(name);
            f.get(0).setDate(date2);
            if(walk!=0)
                f.get(0).setWalk(walk);
            if(distance!=0)
                f.get(0).setDistance(distance);
            if(weight!=0)
                f.get(0).setWeight(weight);
            if(height!=0)
                f.get(0).setHeight(height);
            if(calorie!=0)
                f.get(0).setCalorie(calorie);
            if(!(("".equals(city))|| (city == null)))
                f.get(0).setLocationCity(city);
            if(!(("".equals(town))|| (town == null)))
                f.get(0).setLocationTown(town);
            System.out.println("更新成功");
//            UserData.DataBean dataBean = new UserData.DataBean();
//            dataBean.setWalk(walk);
//            dataBean.setDistance(distance);
//            dataBean.setWeight(weight);
//            dataBean.setHeight(height);
//            dataBean.setCalorie(calorie);
//            dataBean.setLocationCity(city);
//            dataBean.setLocationTown(town);
//            userdata.setData(dataBean);
            ser = session.save(f.get(0));
        }
        //7提交事务
        transaction.commit();
        //8释放资源
        session.close();
        sessionFactory.close();
        if (!ser.equals("0")) {
            userdata.setSuccess(true);
            userdata.setMessage("上传成功");
        } else {
            userdata.setSuccess(false);
            userdata.setMessage("上传失败");
        }
        return userdata;
    }

}
