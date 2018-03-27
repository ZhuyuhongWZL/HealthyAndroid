package com.mrjk.demo.platform.Dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.*;

import com.mrjk.demo.platform.bean.TalkMessageEntity;
import org.hibernate.query.Query;


/**
 * 对保存信息表的操作
 *
 *
 */
public class MessageDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    /**
     * 插入message
     *
     */

    public void insertMessage(TalkMessageEntity tran){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        /*TalkMessageEntity message = new TalkMessageEntity();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        message.setId(tran.getId());
        message.setFrom(tran.getFrom());
        message.setTo(tran.getTo());
        message.setContent(tran.getContent());
        message.setTime(ts);*/
        session.save(tran);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Deprecated
    public TalkMessageEntity sendMessage(String ffrom, String tto){
         cfg.configure();
         sessionFactory = cfg.buildSessionFactory();
         session = sessionFactory.openSession();
         transaction = session.beginTransaction();
         TalkMessageEntity mess = new TalkMessageEntity();
         Query query = session.createQuery("from TalkMessageEntity t where t.ffrom=? and t.tto=?");
         query.setParameter(0, ffrom);
         query.setParameter(1, tto);
         List<TalkMessageEntity> list = query.list();
         mess=list.get(list.size()-1);
         transaction.commit();
         session.close();
         sessionFactory.close();
         return mess;
    }

    /*public List<TalkMessageEntity> getMessages(String fromUser,String toUser,String afterTime){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Query query = session.createQuery("from TalkMessageEntity t where t.ffrom=? and t.tto=? and t.time > '"+afterTime+"'");
        query.setParameter(0, fromUser);
        query.setParameter(1, toUser);
        List<TalkMessageEntity> list = query.list();

        query.setParameter(0, toUser);
        query.setParameter(1, fromUser);
        list.addAll(query.list());

        transaction.commit();
        session.close();
        sessionFactory.close();
        return list;
    }*/
    public List<TalkMessageEntity> getMessages(String fromUser, String toUser, String afterTime) {
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();

        Query query = session.createQuery("from TalkMessageEntity t where t.ffrom=? and t.tto=? and t.time > '" + afterTime + "'");
        query.setParameter(0, fromUser);
        query.setParameter(1, toUser);
        List<TalkMessageEntity> list = query.list();

        query.setParameter(0, toUser);
        query.setParameter(1, fromUser);
        list.addAll(query.list());
        if (list==null || list.size()==0) {
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String tsStr = "0000-00-00 00:00:00";
            try {
                System.out.println(ts);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TalkMessageEntity mess = new TalkMessageEntity();
            List<TalkMessageEntity> list1 = new ArrayList<>();
            mess.setTime(ts);
            mess.setContent("1");
            mess.setTto("error");
            mess.setFfrom("error");
            mess.setId(0);
            list1.add(mess);
            transaction.commit();
            session.close();
            sessionFactory.close();
            return list1;
        } else {

            transaction.commit();
            session.close();
            sessionFactory.close();
            return list;
        }
    }



}

