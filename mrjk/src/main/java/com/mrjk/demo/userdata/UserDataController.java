package com.mrjk.demo.userdata;

import com.google.gson.Gson;
import com.mrjk.demo.food.ApplyDoctor;
import com.mrjk.demo.food.ApplyDoctorDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserDataController {

    @RequestMapping(value = "/user_data/upload",method = RequestMethod.POST)
    String UpdateUserData(@RequestParam(value = "name") String name, @RequestParam(value = "date") String date, @RequestParam(value = "walk") int walk, @RequestParam(value = "distance") Double distance, @RequestParam(value = "weight") Double weight,@RequestParam(value = "height") Double height,@RequestParam(value = "calorie") Double calorie,@RequestParam(value = "city") String city,@RequestParam(value = "town") String town) {

        UserDataUploadDao userDataUploadDao=new UserDataUploadDao();
        UserData userData = userDataUploadDao.UpdateUserData(name,date,walk,distance,weight,height,calorie,city,town);
        Gson gson = new Gson();
        String s = gson.toJson(userData);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/user_data",method = RequestMethod.POST)
    String QueryUserData(@RequestParam(value = "name") String name,@RequestParam(value = "date") String date) {

        UserDataQueryDao userDataQueryDao=new UserDataQueryDao();
        UserData userData  = userDataQueryDao.QueryUserData(name,date);
        Gson gson = new Gson();
        String s = gson.toJson(userData);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/apply_doctor",method = RequestMethod.POST)
    ApplyDoctor applyDoctor(@RequestParam(value = "name") String name,@RequestParam(value = "introduction")String introduction) {
        com.mrjk.demo.userdata.ApplyDoctorDao applyDoctorDao=new com.mrjk.demo.userdata.ApplyDoctorDao();
        ApplyDoctor applydoctor= applyDoctorDao.applyDoctor(name,introduction);
        System.out.println("申请成功");
        return applydoctor;
    }

    @RequestMapping(value = "/apply_doctor/state",method = RequestMethod.POST)
    ApplyDoctor queryStatus(@RequestParam(value = "name") String name) {
        QueryApplystatusDao queryApplystatusDao=new QueryApplystatusDao();
        ApplyDoctor applydoctor=queryApplystatusDao.QueryStatus(name);
        System.out.println("查询成功");
        return applydoctor;
    }

    @RequestMapping(value = "/apply_doctor/out",method = RequestMethod.POST)
    ApplyDoctor doctorLogout(@RequestParam(value = "name") String name) {
        DoctorLogoutDao doctorLogoutDao=new DoctorLogoutDao();
        ApplyDoctor applydoctor=doctorLogoutDao.doctorLogout(name);
        System.out.println("注销成功");
        return applydoctor;
    }

    @RequestMapping(value = "/apply_doctor/handleApplyAllow",method = RequestMethod.POST)
    ApplyDoctor handleApplyallow(@RequestParam(value = "name") String name) {
        ApplyAllowDao applyAllowDao = new ApplyAllowDao();
        ApplyDoctor applydoctor = applyAllowDao.HandleApplyAllow(name);
        System.out.println("认证通过");
        return applydoctor;
    }

    @RequestMapping(value = "/apply_doctor/handleApplyRefuse",method = RequestMethod.POST)
    ApplyDoctor handleApplyrefuse(@RequestParam(value = "name") String name) {
        ApplyRefuseDao applyRefuseDao = new ApplyRefuseDao();
        ApplyDoctor applydoctor=applyRefuseDao.HandleApplyRefuse(name);
        System.out.println("拒绝通过");
        return applydoctor;
    }

}
