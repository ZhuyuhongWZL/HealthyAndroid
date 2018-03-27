package com.mrjk.demo.userdata;

import com.mrjk.demo.food.ApplyDoctor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.mrjk.demo.food.ApplyDoctorEntity;

import java.util.List;

public class QueryApplystatusDao {
    //查询申请状态
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    public ApplyDoctor QueryStatus(String name){
        ApplyDoctor QueryStatus=new ApplyDoctor();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name = '"+name+"'").list();
        if (!f.isEmpty()){
            QueryStatus.setSuccess(true);
            QueryStatus.setMessage("查询成功");
            QueryStatus.setState(f.get(0).getState());
            System.out.println(f.get(0).getState());
        }else {
            QueryStatus.setSuccess(false);
            QueryStatus.setMessage("查询失败");
            System.out.println("对不起，没有您的申请记录");
        }
        session.close();
        sessionFactory.close();
        return QueryStatus;
    }
}
