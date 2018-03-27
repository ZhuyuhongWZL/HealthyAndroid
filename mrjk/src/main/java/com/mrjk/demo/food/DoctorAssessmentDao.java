package com.mrjk.demo.food;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DoctorAssessmentDao {
    Configuration cfg = new Configuration();
    SessionFactory sessionFactory;
    Session session;
    Transaction transaction;
    public DoctorAssessment updateAssessment(String username ,double star ,String doctorname){
        cfg.configure();
        sessionFactory = cfg.buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        DoctorAssessment doctorAssessment=new DoctorAssessment();

        if(star<=5.0&&star>=0){
//            ApplyDoctorEntity applyDoctorEntity=new ApplyDoctorEntity();
//            ApplyDoctorDao applyDoctorDao=new ApplyDoctorDao();
//            List<ApplyDoctorEntity> f = session.createQuery("from ApplyDoctorEntity  A where A.name='"+doctorname+"'").list();
//            if (!f.isEmpty()){
//                int critic_count=0;
//                double oldStar=0.0;
//                for(ApplyDoctorEntity attribute : f) {
//                    critic_count=attribute.getCriticCount();
//                    oldStar=attribute.getStar();
//                    double newStar=((critic_count*oldStar)+star)/(critic_count+1);
//                    applyDoctorEntity.setApplyId(attribute.getApplyId());
//                    applyDoctorEntity.setName(attribute.getName());
//                    applyDoctorEntity.setCreateDate(attribute.getCreateDate());
//                    applyDoctorEntity.setState(attribute.getState());
//                    applyDoctorEntity.setStar(newStar);
//                    applyDoctorEntity.setCriticCount(attribute.getCriticCount()+1);
//                }
//                applyDoctorDao.updateDoctor(applyDoctorEntity);

                DoctorAssessmentEntity doctorAssessmentEntity=new DoctorAssessmentEntity();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String nowTime=sf.format(new Date());// new Date()为获取当前系统时间
                Date date = null;
                try {
                    date = sf.parse(nowTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                java.sql.Timestamp creatTime = new java.sql.Timestamp(date.getTime());
                doctorAssessmentEntity.setUser(username);
                doctorAssessmentEntity.setDoctor(doctorname);
                doctorAssessmentEntity.setStar(star);
                doctorAssessmentEntity.setCreateTime(creatTime);
                //返回值为当前增加的记录的主键值
                Serializable ser=session.save(doctorAssessmentEntity);
                System.out.println("ser="+ser);
                //7提交事务
                transaction.commit();



                if(!ser.equals("0")){
                    doctorAssessment.setSuccess(true);
                    doctorAssessment.setMessage("成功");
                }else {
                    doctorAssessment.setSuccess(false);
                    doctorAssessment.setMessage("失败");
                }

//            }
//            else {
//                doctorAssessment.setSuccess(false);
//                doctorAssessment.setMessage("失败,数据库里没有这个医生");
//                System.out.println("对不起，没有查询到这个医生");
//            }
        }else{
            doctorAssessment.setSuccess(false);
            doctorAssessment.setMessage("失败,评分标准是[0.5]，您的评分不符合标准");
        }


        //8释放资源
        session.close();
        sessionFactory.close();

        return doctorAssessment;
    }
}
