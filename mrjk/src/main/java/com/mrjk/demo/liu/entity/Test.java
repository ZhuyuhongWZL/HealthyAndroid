package com.mrjk.demo.liu.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Date;
import java.sql.Timestamp;

public class Test {

    public static void main(String[] args){


        Configuration cfg= new Configuration();
        cfg.configure();
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        UserEntity userEntity = new UserEntity();

        userEntity.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEntity.setMail("hy");
        userEntity.setPassword("1");
        userEntity.setBirthday(new Date(System.currentTimeMillis()));
        userEntity.setPhone("123");
        userEntity.setName("hy");
        session.save(userEntity);
        transaction.commit();
        session.close();
    }
}
