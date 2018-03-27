package com.mrjk.demo.platform;

import com.google.gson.Gson;
import com.mrjk.demo.platform.Dao.MessageDao;
import com.mrjk.demo.platform.bean.ResponseBean;
import com.mrjk.demo.platform.bean.TalkMessageEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@RestController
public class PlatformController {
    @RequestMapping(value = "/send_message",method = RequestMethod.POST)
    public String sendMessage(@RequestParam(value = "from")String fromUser,@RequestParam(value = "to")String toUser,@RequestParam(value = "content")String content){
        MessageDao dao = new MessageDao();
        TalkMessageEntity entity = new TalkMessageEntity();
        entity.setContent(content);
        entity.setFfrom(fromUser);
        entity.setTto(toUser);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        entity.setTime(timestamp);
        dao.insertMessage(entity);
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }

    @RequestMapping(value = "/get_messages",method = RequestMethod.POST)
    public String getMessages(@RequestParam(value = "user")String user,@RequestParam(value = "user1")String user1,@RequestParam(value = "after_time")String afterTime){
        MessageDao dao = new MessageDao();
        List<TalkMessageEntity> l = dao.getMessages(user,user1,afterTime);
        ResponseBean responseBean = new ResponseBean();
        responseBean.setSuccess(true);
        List<ResponseBean.MessagesBean> messagesBeans = new ArrayList<>();
        Iterator<TalkMessageEntity> iterator = l.iterator();
        while (iterator.hasNext()){
            TalkMessageEntity entity = iterator.next();
            ResponseBean.MessagesBean bean = new ResponseBean.MessagesBean();
            bean.setFrom(entity.getFfrom());
            bean.setTo(entity.getTto());
            bean.setContent(entity.getContent());
            bean.setTime(entity.getTime().toString());
            messagesBeans.add(bean);
        }
        Collections.sort(messagesBeans);
        responseBean.setMessages(messagesBeans);
        return new Gson().toJson(responseBean);
    }


}
