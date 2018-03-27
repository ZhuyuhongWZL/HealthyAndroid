package com.mrjk.demo.statistic;

import com.mrjk.demo.food.ApplyDoctorEntity;
import com.mrjk.demo.food.DoctorAssessmentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;



import java.sql.Timestamp;
import java.util.*;


/**
 * 用于统计医生评分
 */
public class DoctorStarStatisticTask extends TimerTask {
    private Configuration cfg;
    private SessionFactory sessionFactory;

    //检查点
    private static long STATISTIC_POINT = System.currentTimeMillis();
    //每隔多长时间检查
    private static long INTERVAL = 1000*50;


    public static void start(){
        Timer timer = new Timer();
        timer.schedule(new DoctorStarStatisticTask(),5000,INTERVAL);
    }

    private static String SQL = "FROM DoctorAssessmentEntity d where d.createTime > ?";

    public DoctorStarStatisticTask() {
        cfg = new Configuration();
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    @Override
    public void run() {
        Timestamp timestamp = new Timestamp(STATISTIC_POINT);
        Map<String,DataBean> map = new HashMap<>();

        Session session = sessionFactory.openSession();
        Query query = session.createQuery(SQL);
        query.setParameter(0,timestamp);

        List<DoctorAssessmentEntity> l = query.list();
        Iterator<DoctorAssessmentEntity> i = l.iterator();

        while (i.hasNext()){
            DoctorAssessmentEntity entity = i.next();
            if (map.containsKey(entity.getDoctor())){
                DataBean dataBean = map.get(entity.getDoctor());
                dataBean.setCount(dataBean.getCount()+1);
                dataBean.setTotalStar(dataBean.getTotalStar()+entity.getStar());
            }else {
                DataBean dataBean = new DataBean();
                dataBean.setCount(1);
                dataBean.setTotalStar(entity.getStar());
                map.put(entity.getDoctor(),dataBean);
            }
        }
        session.close();

        for (Map.Entry<String,DataBean> entry:map.entrySet()){
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Query query1 = session.createQuery("from ApplyDoctorEntity d where d.name = ?");
            query1.setParameter(0,entry.getKey());
            List<ApplyDoctorEntity> list = query1.list();

            assert list.size() == 1:"error";

            ApplyDoctorEntity entity = list.get(0);
            int count = entity.getCriticCount() == null ?0:entity.getCriticCount();
            double oldStar = entity.getStar() == null?0.0:entity.getStar();
            entity.setCriticCount(count + entry.getValue().getCount());
            double star =( oldStar*count + entry.getValue().getTotalStar() )/(count + entry.getValue().getCount());
            entity.setStar(star);

            session.update(entity);
            transaction.commit();
            session.close();
        }

        STATISTIC_POINT += INTERVAL;
    }

    private static class DataBean{
        private int count;//评论人数
        private double totalStar;//总分

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public double getTotalStar() {
            return totalStar;
        }

        public void setTotalStar(double totalStar) {
            this.totalStar = totalStar;
        }

        public DataBean() {
        }
    }

}
