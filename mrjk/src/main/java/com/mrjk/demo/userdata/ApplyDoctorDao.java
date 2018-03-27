package com.mrjk.demo.userdata;

import com.mrjk.demo.food.ApplyDoctorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ApplyDoctorDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    //申请成为医生
    public com.mrjk.demo.food.ApplyDoctor applyDoctor(String name,String introduction){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        com.mrjk.demo.food.ApplyDoctor applydoctor=new com.mrjk.demo.food.ApplyDoctor();
        ApplyDoctorEntity apply=new ApplyDoctorEntity();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name = '"+name+"'").list();
        if (f.isEmpty()){
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String nowTime=sf.format(new Date());// new Date()为获取当前系统时间
            Date date = null;
            try {
                date = sf.parse(nowTime);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            java.sql.Timestamp date1 = new java.sql.Timestamp(date.getTime());
            apply.setName(name);
            apply.setCreateDate(date1);
            apply.setState("审核中");
            apply.setStar(0.0);
            apply.setCriticCount(0);
            apply.setState(apply.getState());
            apply.setIntroduction(introduction);
            //返回值为当前增加的记录的主键值
            Serializable ser=session.save(apply);
            //7提交事务
            transaction.commit();
            if(!ser.equals("0")){
                applydoctor.setSuccess(true);
                applydoctor.setMessage("已发出申请");
            }else {
                applydoctor.setSuccess(false);
                applydoctor.setMessage("申请发出失败");
            }
        }else {
            applydoctor.setSuccess(false);
            applydoctor.setMessage("申请失败");
            System.out.println("您已经申请过了哦！");
        }
        //8释放资源
        session.close();
        sessionFactory.close();

        return applydoctor;

    }
}
