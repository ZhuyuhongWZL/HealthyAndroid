package com.mrjk.demo.food;

import com.google.gson.Gson;

public class Testsql {
    public static void main(String[] args){
//        //1.加载HIbernate核心配置文件
//        Configuration cfg = new Configuration();
//        cfg.configure();
//        //2.使用sessionFactory
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        //3使用sessionfactory 实例化session对象
//        Session session = sessionFactory.openSession();
//        //4.开始事务
//        Transaction transaction = session.beginTransaction();
//
//        //7.close  Database resources
//        List<FoodEntity> f = session.createQuery("from FoodEntity  F where F.name = '色拉油' ").list();
//        if (!f.isEmpty()){
//            System.out.println(f.get(0).getCalorie());
//        }
//
////        FoodEntity s = session.get(FoodEntity.class, 1);
////        System.out.println("-----" + s.getId() + "\t" + s.getName() + "\t"
////              + s.getCalorie() + "\t" + s.getAlias());
//
//
//        session.close();
//        sessionFactory.close();

        //查询食物热量
        FoodDao foodDao=new FoodDao();
        Food food = foodDao.selectByName("米");
        System.out.println(food.getMessage());
        Gson gson =new Gson();
        String s = gson.toJson(food);

        //上传食物热量
//        FoodupdateDao foodupdateDao=new FoodupdateDao();
//        Foodupdate foodupdate = foodupdateDao.updateFood("黄焖鸡米饭","",500,"雷向犇");
//        Gson gson =new Gson();
//        String s = gson.toJson(foodupdate);

    }
}
