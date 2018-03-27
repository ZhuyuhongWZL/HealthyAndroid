package com.mrjk.demo.food;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApplyDoctorDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    public void updateDoctor(ApplyDoctorEntity ApplyDoctorEntity){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.update(ApplyDoctorEntity);
        transaction.commit();

        session.close();
        sessionFactory.close();

    }
    public  ApplyDoctor selectDoctorByStar(Double star){
        ApplyDoctor applyDoctor=new ApplyDoctor();
        List<ApplyDoctorEntity> doctors=new ArrayList<>();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.star>="+star+"").list();
        if (!f.isEmpty()){
            applyDoctor.setSuccess(true);
            applyDoctor.setMessage("成功");
        }else {
            applyDoctor.setSuccess(false);
            applyDoctor.setMessage("失败");
            System.out.println("对不起，没有查询到此类医生");
        }
        for(ApplyDoctorEntity attribute : f) {
            doctors.add(attribute);
            System.out.println("医生："+attribute.getName());
        }
        applyDoctor.setDoctors(doctors);
        session.close();
        sessionFactory.close();
        return applyDoctor;
    }


    public ApplyDoctor selectDoctorByName(String name){
        ApplyDoctor applyDoctor=new ApplyDoctor();
        List<ApplyDoctorEntity> doctors=new ArrayList<>();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name like '%"+name+"%'").list();
        if (!f.isEmpty()){
            applyDoctor.setSuccess(true);
            applyDoctor.setMessage("成功");
        }else {
            applyDoctor.setSuccess(false);
            applyDoctor.setMessage("失败");
            System.out.println("对不起，没有查询到此类医生");
        }
        for(ApplyDoctorEntity attribute : f) {
            doctors.add(attribute);
            System.out.println("医生："+attribute.getName());
        }
        applyDoctor.setDoctors(doctors);
        session.close();
        sessionFactory.close();
        return applyDoctor;
    }

}
