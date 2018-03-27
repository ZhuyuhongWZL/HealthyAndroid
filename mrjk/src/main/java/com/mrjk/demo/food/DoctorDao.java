package com.mrjk.demo.food;

import com.mrjk.demo.doctor.DoctorUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.List;

public class DoctorDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    public boolean isFriend(String user,String doctor){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        boolean isFriend=true;
        List<DoctorUserEntity> f = session.createQuery("from DoctorUserEntity  D where D.user = '"+user+"' AND D.doctor='"+doctor+"'").list();
        if (!f.isEmpty()) isFriend=true;
        else isFriend=false;
        session.close();
        sessionFactory.close();
        return isFriend;
    }
    public  void deleteDoctor(String user,String doctor){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        List<DoctorUserEntity> f = session.createQuery("from DoctorUserEntity  D where D.user = '"+user+"' AND D.doctor='"+doctor+"'").list();
        if (!f.isEmpty()){
            for(int i=0;i<f.size();i++){
                DoctorUserEntity doctorUserEntity = (DoctorUserEntity)session.get(DoctorUserEntity.class,f.get(i).getId());
                if(doctorUserEntity!= null ){
                    session.delete(doctorUserEntity);
                }
            }
        }
        else ;


//        session.delete(doctorUserEntity);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
