package com.mrjk.demo.userdata;

import com.mrjk.demo.food.ApplyDoctor;
import com.mrjk.demo.food.ApplyDoctorEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.util.List;

public class ApplyAllowDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    //通过医生认证
    public ApplyDoctor HandleApplyAllow(String name){
        ApplyDoctor handle=new ApplyDoctor();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name = '"+name+"'").list();
        f.get(0).setState("审核通过");
        Serializable ser=session.save(f.get(0));
        //7提交事务
        transaction.commit();
        if(!ser.equals("0")){
            handle.setSuccess(true);
            handle.setMessage("审核成功！");
        }else {
            handle.setSuccess(false);
            handle.setMessage("审核失败！");
        }
        session.close();
        sessionFactory.close();
        return handle;
    }
}
