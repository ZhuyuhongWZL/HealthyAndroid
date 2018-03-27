package com.mrjk.demo.doctor;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mrjk.demo.food.ApplyDoctorEntity;
import com.mrjk.demo.liu.entity.UserEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@RestController
public class DoctorApplyController {
    public static final String DOCTOR_UPLOAD_DIR="F:\\mrjk\\src\\main\\resources\\static\\doctor\\";
    private Pattern pattern = Pattern.compile("(.*)\\.(.*)");

    @RequestMapping(value = "/doctor_upload",method = RequestMethod.POST)
    public String uploadPic(@RequestParam(value = "introduction") String introduction,
                            @RequestParam(value = "name") String userName,
                            @RequestParam(value="file")MultipartFile file){
        DoctorDao.updateIntroduction(userName,introduction);
        try {
            uploadFile(file,userName);
        } catch (IOException e) {
            return "{\"sussess\":false,\"message\":\"失败\"}";
        }
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }


    @RequestMapping(value = "/get_user_by_name",method = RequestMethod.POST)
    public String getUserBeanByName(@RequestParam(value = "name")String name){
        List<UserEntity> list =  DoctorDao.getUserByName(name);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        String s = gson.toJson(list);
        return s;
 //       return new Gson().toJson(list);

    }

    @RequestMapping(value = "/get_doctor_by_name",method = RequestMethod.POST)
    public String getDoctorBeanByName(@RequestParam(value = "name")String name){
        List<ApplyDoctorEntity> list =  DoctorDao.getDoctoryName(name);
        return new Gson().toJson(list);
    }

    @RequestMapping(value = "/add_doctor",method = RequestMethod.POST)
    public String addDoctor(@RequestParam(value = "user")String user,@RequestParam(value = "doctor")String doctor){
        DoctorDao.addDoctor(user,doctor);
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }


    @RequestMapping(value = "/get_friends",method = RequestMethod.POST)
    public String getDoctor(@RequestParam(value = "user")String user){
        return new Gson().toJson(DoctorDao.getDoctors(user));
    }

    public void uploadFile( MultipartFile file,String userName) throws IOException {
        Matcher matcher = pattern.matcher(file.getOriginalFilename());
        matcher.find();
        File in = new File(DOCTOR_UPLOAD_DIR + userName + ".zip");
        File dest = in.getParentFile();
        if (!dest.exists()){
            dest.mkdirs();
        }
        file.transferTo(in);
    }
    @RequestMapping(value = "/get_friends_by_doctor",method = RequestMethod.POST)
    public String getFriendsByDoctor(@RequestParam(value = "doctor")String doctor){
        return new Gson().toJson(DoctorDao.getFriendsByDoctor(doctor));
    }
}
