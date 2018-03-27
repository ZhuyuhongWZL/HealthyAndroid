package com.mrjk.demo.liu.controller;

import com.mrjk.demo.liu.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class datebaseDao {
    Configuration cfg;
    {
        cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;

    public void insert(UserEntity u){
        session =sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.update(u);
        transaction.commit();
        session.close();
        //sessionFactory.close();
    }

    public List<UserEntity> findByName(String name){
        //cfg.configure();
        //sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        UserEntity userEntity=new UserEntity();
//        userEntity.FoodBean ffb =new Food.FoodBean();
//
//       List<Food.FoodBean> foodBeanList = new ArrayList<Food.FoodBean>();



        List<UserEntity> U = session.createQuery("from UserEntity  F where F.name ='"+name +"'").list();

//        food.setFood(foodBeanList);
        session.close();
//        sessionFactory.close();

        return U;

    }

    public List<UserEntity> findByMail(String mail){
//        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
//        userEntity.FoodBean ffb =new Food.FoodBean();
//
//       List<Food.FoodBean> foodBeanList = new ArrayList<Food.FoodBean>();



        List<UserEntity> U = session.createQuery("from UserEntity  F where F.mail ='"+mail+"'").list();
//        food.setFood(foodBeanList);
        session.close();
//        sessionFactory.close();
        return U;

    }
    public void inserts(UserEntity u){
        session =sessionFactory.openSession();
        transaction = session.beginTransaction();
        System.out.println("-->"+u.getCreateTime());
        session.save(u);
        transaction.commit();
        session.close();
    }

}
