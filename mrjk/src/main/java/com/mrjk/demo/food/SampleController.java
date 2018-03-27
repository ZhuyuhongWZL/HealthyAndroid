package com.mrjk.demo.food;

import com.google.gson.Gson;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

@RestController
public class SampleController {
//    @RequestMapping("/")
//    @ResponseBody
//    String home() {
//        return "Hello World!";
//    }
//
//    @RequestMapping(value = "/test",method = RequestMethod.GET)
//    String hello() {
//        return "/test";
//    }

    @Deprecated
    public void uploadFile( MultipartFile file) {
        try {
            String parentFile = "E:\\test2\\";
            File in = new File(parentFile + file.getOriginalFilename());
            File dest = in.getParentFile();
            if (!dest.exists()) //如果这个文件不存在
            {
                dest.mkdirs(); //创建
            }
            file.transferTo(in); // copy
//            boolean isFileDelete = in.delete(); // delete file
//            if (!isFileDelete) //删除失败
//            {
//                System.out.println("删除失败");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Deprecated
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    String test(@RequestParam(value = "file") MultipartFile file) {
        uploadFile(file);
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }


    @RequestMapping(value = "/food",method = RequestMethod.POST)
    String selectFood(@RequestParam(value = "name") String name) {

        FoodDao foodDao=new FoodDao();
        Food food = foodDao.selectByName(name);
        System.out.println(food.getMessage());
        Gson gson =new Gson();
        String s = gson.toJson(food);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/food/upload",method = RequestMethod.POST)
    String updateFood(@RequestParam(value = "name") String name,@RequestParam(value = "alias") String alias,@RequestParam(value = "calorie") int calorie,@RequestParam(value = "uploader") String uploader) {

        FoodupdateDao foodupdateDao=new FoodupdateDao();
        Foodupdate foodupdate = foodupdateDao.updateFood(name,alias,calorie,uploader);
        Gson gson =new Gson();
        String s = gson.toJson(foodupdate);
        System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/find_doctor",method = RequestMethod.POST)
    String selectDoctorByStar(@RequestParam(value = "star") double star) {

        ApplyDoctorDao applyDoctorDao=new ApplyDoctorDao();
        ApplyDoctor applyDoctor=applyDoctorDao.selectDoctorByStar(star);
        Gson gson =new Gson();
        String s = gson.toJson(applyDoctor);
        //System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/find_doctor_by_name",method = RequestMethod.POST)
    String selectDoctorByName(@RequestParam(value = "name") String name) {

        ApplyDoctorDao applyDoctorDao=new ApplyDoctorDao();
        ApplyDoctor applyDoctor=applyDoctorDao.selectDoctorByName(name);
        Gson gson =new Gson();
        String s = gson.toJson(applyDoctor);
        //System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/critic_doctor",method = RequestMethod.POST)
    String selectFood(@RequestParam(value = "username") String username,@RequestParam(value = "star")double star,@RequestParam(value = "doctorname") String doctorname) {
        DoctorAssessmentDao doctorAssessmentDao=new DoctorAssessmentDao();
        DoctorAssessment doctorAssessment=doctorAssessmentDao.updateAssessment(username,star,doctorname);
        Gson gson =new Gson();
        String s = gson.toJson(doctorAssessment);
        //System.out.println(s);
        return s;
    }

    @RequestMapping(value = "/delete_doctor",method = RequestMethod.POST)
    public String deleteDoctor(@RequestParam(value = "user")String user,@RequestParam(value = "doctor")String doctor){
        new DoctorDao().deleteDoctor(user,doctor);
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }

    @RequestMapping(value = "/isFriend",method = RequestMethod.POST)
    public boolean isFriend(@RequestParam(value = "user")String user,@RequestParam(value = "doctor")String doctor){
        return new DoctorDao().isFriend(user,doctor);
    }

}
