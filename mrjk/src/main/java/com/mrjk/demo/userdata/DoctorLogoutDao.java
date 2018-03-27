package com.mrjk.demo.userdata;

import com.mrjk.demo.food.ApplyDoctor;
import com.mrjk.demo.food.ApplyDoctorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public class DoctorLogoutDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    //医生资格注销
    public ApplyDoctor doctorLogout(String name){
        ApplyDoctor Logout=new ApplyDoctor();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name = '"+name+"'").list();
        f.get(0).setState("已注销");
        Serializable ser=session.save(f.get(0));
        //7提交事务
        transaction.commit();
        if(!ser.equals("0")){
            Logout.setSuccess(true);
            Logout.setMessage("注销成功！");
        }else {
            Logout.setSuccess(false);
            Logout.setMessage("注销失败！");
        }
        session.close();
        sessionFactory.close();
        return Logout;
    }
}
