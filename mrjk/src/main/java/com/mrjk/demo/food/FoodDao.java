package com.mrjk.demo.food;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
public class FoodDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;



    public String selectById(int id){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        String result="";

        List<FoodEntity> f = session.createQuery("from FoodEntity  F where F.id = "+id+" ").list();
        if (!f.isEmpty()){
            result=f.get(0).getName()+"的热量为"+f.get(0).getCalorie()+"千卡";
        }
        else result="对不起，数据库没有这个食物的数据";
        session.close();
        sessionFactory.close();
        return result;
    }

    public Food selectByName(String name){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Food food=new Food();
        if(name.equals("")){
            food.setSuccess(false);
            food.setMessage("失败");
        }else{

            List<Food.FoodBean> foodBeanList = new ArrayList<Food.FoodBean>();
            List<FoodEntity> f = session.createQuery("from FoodEntity  F where F.name like'%"+name+"%' OR F.alias like '%"+name+"%'").list();
            if (!f.isEmpty()){
                food.setSuccess(true);
                food.setMessage("成功");
            }else {
                food.setSuccess(false);
                food.setMessage("失败");
                System.out.println("对不起，数据库没有这个食物的数据");
            }
            for(FoodEntity attribute : f) {
                Food.FoodBean ffb =new Food.FoodBean();
                ffb.setName(attribute.getName());
                ffb.setAlias(attribute.getAlias());
                ffb.setCalorie(attribute.getCalorie());
                foodBeanList.add(ffb);
                System.out.println(attribute.getName()+"的热量为"+attribute.getCalorie());
            }
            food.setFood(foodBeanList);
        }

        session.close();
        sessionFactory.close();

        return food;

    }

}
