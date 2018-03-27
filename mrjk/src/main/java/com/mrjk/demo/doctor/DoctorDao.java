package com.mrjk.demo.doctor;

import com.mrjk.demo.food.ApplyDoctorEntity;
import com.mrjk.demo.liu.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.Timestamp;
import java.util.List;


public class DoctorDao {
    public static void updateIntroduction(String userName,String introdution){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from ApplyDoctorEntity d where d.name = ?");
        query.setParameter(0,userName);
        List<ApplyDoctorEntity> l = query.list();
        assert  l.size() == 1:"error";
        ApplyDoctorEntity entity=l.get(0);
        entity.setIntroduction(introdution);
        session.update(entity);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public static List<UserEntity> getUserByName(String name){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from UserEntity u where u.name = ?");
        query.setParameter(0,name);
        List<UserEntity> list = query.list();
        transaction.commit();
        session.close();
        sessionFactory.close();
        System.out.println(list.get(0).getBirthday());
        return list;
    }

    public static List<ApplyDoctorEntity> getDoctoryName(String name) {
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from ApplyDoctorEntity u where u.name like '%"+name+"%'");
        List<ApplyDoctorEntity> list = query.list();
        transaction.commit();
        session.close();
        sessionFactory.close();


        return list;
    }



    public static void addDoctor(String user,String doctor){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        DoctorUserEntity entity = new DoctorUserEntity();
        entity.setUser(user);
        entity.setDoctor(doctor);
        entity.setCreateTime(new Timestamp(System.currentTimeMillis()));

        session.save(entity);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    public static List<DoctorUserEntity> getDoctors(String user){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from DoctorUserEntity u where u.user =?");
        query.setParameter(0,user);
        List<DoctorUserEntity> list = query.list();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return list;
    }
    public static List<DoctorUserEntity> getFriendsByDoctor(String doctor){
        Configuration cfg = new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from DoctorUserEntity u where u.doctor =? ");
        query.setParameter(0,doctor);

        List<DoctorUserEntity> list = query.list();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return list;
    }
}
