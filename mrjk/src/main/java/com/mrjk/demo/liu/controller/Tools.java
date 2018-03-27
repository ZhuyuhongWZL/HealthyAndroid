package com.mrjk.demo.liu.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public boolean isEmail(String email){
        if (null==email || "".equals(email)) return false;
//        Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p =  Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public int newVerify(){
        return (int) (((Math.random() * 9) + 1) * 100000);
    }

    public Date stringToDate(String source) throws ParseException {
        if(source==null){
            return null;
        }
        else{SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd");
        String s= "2011-07-09";
        java.util.Date date1 = formatter.parse(source);
//        java.util.Date date1=formatter.parse(source);
        Date date = new Date(date1.getTime());
            return date;}

    }

    public String DatetoString(Date date) throws ParseException {
        if(date==null){
            return null;
        }
        else {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
            String date1 = formatter.format(date);//格式化数据
            return date1;
        }
        }

    public String getVerify(String mail,String[] mails,String[] verifys) {
        int l=0;
       for(int i = 0;i<10;i++){
           if(mails[i].equals(mail)){
               l=i;
           }
       }
       return verifys[l];
    }
}
