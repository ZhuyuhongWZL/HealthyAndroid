package com.mrjk.demo.platform.xjtu;

import com.mrjk.demo.platform.Dao.MessageDao;
import com.mrjk.demo.platform.bean.TalkMessageEntity;

import java.sql.Timestamp;

import static java.sql.Types.NULL;

public class user1 {
    public static void main(String[] args) {
        TalkMessageEntity message = new TalkMessageEntity();
        message.setId(9);
         message.setFfrom("45");
         message.setTto("56");
         message.setContent("7777777");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        message.setTime(ts);
         MessageDao messageDao=new MessageDao();
         messageDao.insertMessage(message);
    }
}
