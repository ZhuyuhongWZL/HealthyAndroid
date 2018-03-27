package com.mrjk.demo.liu.controller;


import com.google.gson.Gson;
import com.mrjk.demo.liu.entity.Bean;
import com.mrjk.demo.liu.entity.Mail;
import com.mrjk.demo.liu.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;


@RestController
public class MainController1 {


    private datebaseDao dDao = new datebaseDao();

    private Bean requestData = new Bean();
    private  Tools tools = new Tools();
    private Bean bean = new Bean();
    private Bean.UserBean userBean = new Bean.UserBean();
    private int verify;
    private String s="";
    private String []mails = new String[10];
    private String []verifys = new String[10];
    private int i =0;

//    @GetMapping(value = "/hello")
//    public List<UserEntity> getStudents(){
//        System.out.println("你好");
////        return userRepository.findAll();
//    }

    @RequestMapping(value = "/register")
    public String insertInto(@RequestParam("name")String name, @RequestParam("password") String password, @RequestParam("mail") String mail, @RequestParam("repasswd") String repasswd) throws Exception {

        List<UserEntity> userList;
        List<UserEntity> userList1;
        userList = dDao.findByName(name);
        userList1 = dDao.findByMail(mail);
//        userList = userRepository.findByName(name);
     if(!userList.isEmpty()){
         requestData.setMessage("用户名重复");
         return new Gson().toJson(requestData);
     }else if(!userList1.isEmpty()){
         requestData.setMessage("邮箱重复");
         return new Gson().toJson(requestData);
     }
//        if(name.equals("")) {
//      requestData.setMessage("请输入用户名");
//            return new Gson().toJson(requestData);
//        }else if(name.equals("")) {
//            requestData.setMessage("请输入密码");
//            return new Gson().toJson(requestData);
//        }else if(name.equals("")) {
//            requestData.setMessage("请输入重复密码");
//            return new Gson().toJson(requestData);
//        }else if(name.equals("")) {
//            requestData.setMessage("请输入邮箱");
//            return new Gson().toJson(requestData);
//        }else if(!password.equals(repasswd)) {
//            requestData.setMessage("两次输入的密码不一致");
//            return new Gson().toJson(requestData);
//        }
//        else if(!tools.isEmail(mail)) {
//            requestData.setMessage("邮箱不合法");
//            return new Gson().toJson(requestData);
//        }
        else{
         Timestamp d = new Timestamp(System.currentTimeMillis());
         System.out.println("你好"+d);

         verify = tools.newVerify();
            s=verify+"";
            for(int k = 0;k<10;k++){
                mails[k] = new String();
                verifys[k] = new String();
            }
            mails[i]=mail;
            verifys[i]=s;
            i++;
            System.out.println(s);
            Mail sendMail = new Mail(mail,"","验证码",s);
            sendMail.send();
            requestData.setMessage("注册成功，立即验证");
            UserEntity userEntity = new UserEntity();
            userEntity.setCreateTime(d);
            userEntity.setState("未验证");
            userEntity.setName(name);
            userEntity.setPassword(password);
            userEntity.setMail(mail);

            userBean.setName(userEntity.getName());
            userBean.setPassword(userEntity.getPassword());
            userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
            userBean.setMail(userEntity.getMail());
            userBean.setPhone(userEntity.getPhone());
            userBean.setSex(userEntity.getSex());
            userBean.setState(userEntity.getState());
            userBean.setCreateTime(userEntity.getCreateTime().toString());

            requestData.setUser(userBean);
            dDao.inserts(userEntity);
//            userRepository.save(userEntity);
            return new Gson().toJson(requestData);
        }

    }


    @PostMapping(value = "/login")
    public String login(@RequestParam("name")String name, @RequestParam("password") String password) throws ParseException {
       if(!name.contains("@")){
           List<UserEntity> userList;
           userList = dDao.findByName(name);
//        userList = userRepository.findByName(name);

           if(userList.isEmpty()){
               requestData.setMessage("用户名不存在");
               bean.setUser(null);
               return new Gson().toJson(requestData);
           }else{
               UserEntity userEntity = userList.get(0);
               if(!password.equals(userEntity.getPassword())) {
                   requestData.setMessage("密码错误");
                   bean.setUser(null);
                   return new Gson().toJson(requestData);
               }else if(userEntity.getState().equals("未验证")){
                   bean.setUser(null);
           requestData.setMessage("请验证");
                   return new Gson().toJson(requestData);
               }else{

                   userBean.setName(userEntity.getName());
                   userBean.setPassword(userEntity.getPassword());
                   userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
                   userBean.setMail(userEntity.getMail());
                   userBean.setPhone(userEntity.getPhone());
                   System.out.println(userEntity.getPhone());
                   userBean.setSex(userEntity.getSex());
                   userBean.setState(userEntity.getState());
                   userBean.setCreateTime(userEntity.getCreateTime().toString());
                   requestData.setUser(userBean);
                  requestData.setMessage("登录成功");
//                   userBean.setName(userEntity.getName());
//                   userBean.setPassword(userEntity.getPassword());
//                   userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
//                   userBean.setMail(userEntity.getMail());
//                   userBean.setPhone(userEntity.getPhone());
//                   userBean.setSex(userEntity.getSex());
//                   userBean.setState(userEntity.getState());
//                   userBean.setCreateTime(userEntity.getCreateTime().toString());

                   return new Gson().toJson(requestData);
               }

           }


       }else{
           List<UserEntity> userList1;
           userList1 = dDao.findByMail(name);
//        userList = userRepository.findByName(name);
           if(userList1.isEmpty()){
               requestData.setMessage("邮箱不存在");
               bean.setUser(null);
               return new Gson().toJson(requestData);
           }else{
               UserEntity userEntity = userList1.get(0);
               if(!password.equals(userEntity.getPassword())) {
                   requestData.setMessage("密码错误");
                   bean.setUser(null);
                   return new Gson().toJson(requestData);
               }else if(userEntity.getState().equals("未验证")) {
                   bean.setUser(null);
                   requestData.setMessage("请验证");
                   return new Gson().toJson(requestData);
               }else{


                   userBean.setName(userEntity.getName());
                   userBean.setPassword(userEntity.getPassword());
                   userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
                   userBean.setMail(userEntity.getMail());
                   userBean.setPhone(userEntity.getPhone());
                   userBean.setSex(userEntity.getSex());
                   userBean.setState(userEntity.getState());
                   userBean.setCreateTime(userEntity.getCreateTime().toString());
                   requestData.setMessage("登录成功");
//                   userBean.setName(userEntity.getName());
//                   userBean.setPassword(userEntity.getPassword());
//                   userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
//                   userBean.setMail(userEntity.getMail());
//                   userBean.setPhone(userEntity.getPhone());
//                   userBean.setSex(userEntity.getSex());
//                   userBean.setState(userEntity.getState());
//                   userBean.setCreateTime(userEntity.getCreateTime().toString());
                   return new Gson().toJson(requestData);
               }
           }

       }
//        if(name.equals("")) {
//            requestData.setMessage("请输入用户名");
//            return new Gson().toJson(requestData);
//        }else if(password.equals("")) {
//            requestData.setMessage("请输入密码");
//            return new Gson().toJson(requestData);
//        }else if(!password.equals(userEntity.getPassword())) {
//            requestData.setMessage("密码错误");
//            return new Gson().toJson(requestData);
//        }else if(userEntity.getState().equals("未验证")) {
//            requestData.setMessage("请验证");
//            return new Gson().toJson(requestData);
//        }else{
//            requestData.setMessage("登录成功");
//            return new Gson().toJson(requestData);
//        }

    }
//
//
    @PostMapping(value = "/verify")
    public String verify(@RequestParam("verify")String verify,@RequestParam("mail")String mail) throws ParseException {
        List<UserEntity> userList;
        userList = dDao.findByMail(mail);
//        userList = userRepository.findByMail(mail);
        UserEntity userEntity = userList.get(0);
        if(!verify.equals(tools.getVerify(mail,mails,verifys))) {
            requestData.setMessage("请输入正确的验证码");
            bean.setUser(null);
            return new Gson().toJson(requestData);
        }else{
            userEntity.setState("已验证");
            dDao.insert(userEntity);
            userBean.setName(userEntity.getName());
            userBean.setPassword(userEntity.getPassword());
            userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
            userBean.setMail(userEntity.getMail());
            userBean.setPhone(userEntity.getPhone());
            userBean.setSex(userEntity.getSex());
            userBean.setState(userEntity.getState());
            userBean.setCreateTime(userEntity.getCreateTime().toString());
            requestData.setMessage("验证成功，立即登录");
//            userRepository.save(userEntity);
            return new Gson().toJson(requestData);
        }

        //       return studentRepository.findAll();
    }


    @PostMapping(value = "/change_user")
    public String change_user(@RequestParam("name")String name, @RequestParam(value="password",required =false) String password, @RequestParam(value="mail",required =false) String mail, @RequestParam(value="birthday",required =false) String birthday, @RequestParam(value="sex",required =false) String sex, @RequestParam(value="phone",required =false) String phone) throws ParseException {

        List<UserEntity> userList;

        userList = dDao.findByName(name);

//        userList = userRepository.findByName(name);
        UserEntity userEntity = userList.get(0);
        Date date = tools.stringToDate(birthday);
//        if(password!=null){
//
//        }
//        userEntity.setPassword(password);
//        userEntity.setBirthday(date);
//        userEntity.setSex(sex);
//        userEntity.setPhone(phone);
//        userEntity.setMail(mail);
        if(password!=null){
            userEntity.setPassword(password);

        }
        if(birthday!=null){
           userEntity.setBirthday(date);

        }
        if(sex!=null){
            userEntity.setSex(sex);

        }
        if(phone!=null){
           userEntity.setPhone(phone);

        }
        if(mail!=null){
           userEntity.setMail(mail);
        }
        dDao.insert(userEntity);
//        userRepository.save(userEntity);
        requestData.setMessage("修改成功");

        userBean.setName(userEntity.getName());
        userBean.setPassword(userEntity.getPassword());
        userBean.setBirthday(tools.DatetoString(userEntity.getBirthday()));
        userBean.setMail(userEntity.getMail());
        userBean.setPhone(userEntity.getPhone());
        userBean.setSex(userEntity.getSex());
        userBean.setState(userEntity.getState());
        userBean.setCreateTime(userEntity.getCreateTime().toString());
        requestData.setUser(userBean);
//        dDao.insert(userEntity);
//        userRepository.save(userEntity);

        return new Gson().toJson(requestData);
    }
}
