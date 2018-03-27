package com.mrjk.demo.food;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodupdateDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    public Foodupdate updateFood(String name,String alias,int calorie,String uploader){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Foodupdate foodupdate=new Foodupdate();
        FoodUploadEntity fue=new FoodUploadEntity();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String nowTime=sf.format(new Date());// new Date()为获取当前系统时间
        Date date = null;
        try {
            date = sf.parse(nowTime);
            } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Timestamp uploadTime = new java.sql.Timestamp(date.getTime());
        fue.setName(name);
        fue.setAlias(alias);
        fue.setCalorie(calorie);
        fue.setUploader(uploader);
        fue.setUploadTime(uploadTime);
        //返回值为当前增加的记录的主键值
        Serializable ser=session.save(fue);
        System.out.println("ser="+ser);
        //7提交事务
        transaction.commit();
        //8释放资源
        session.close();
        sessionFactory.close();
        if(!ser.equals("0")){
            foodupdate.set_$Success173(true);
            foodupdate.setMessage("成功");
        }else {
            foodupdate.set_$Success173(false);
            foodupdate.setMessage("失败");
        }

        return foodupdate;

    }

}
