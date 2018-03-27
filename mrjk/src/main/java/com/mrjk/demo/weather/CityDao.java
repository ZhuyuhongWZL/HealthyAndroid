package com.mrjk.demo.weather;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CityDao {
    public static void queryCity(String cityName,
                                 String townName,
                                 CallBack callBack) {
        SessionFactory sf =
                new Configuration().configure().buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        List<CityEntity> l = session.createQuery("from CityEntity  C where C.cityName = '" + cityName + "' and C.townName = '" + townName + "'").list();
        if (!l.isEmpty()){
            callBack.onGetObj(l.get(0));
        }
        tx.commit();
        session.close();
        sf.close();
    }

    public static interface CallBack {
        void onGetObj(Object object);
    }

    public static void main(String[] args) {
        CityDao.queryCity("成都", "成都", new CallBack() {
            @Override
            public void onGetObj(Object object) {
                if (!(object instanceof CityEntity)) {
                    throw new IllegalStateException("the obj is not a CityEntity");
                }
                System.out.println("--->"+((CityEntity) object).getId());
            }
        });
    }

}


