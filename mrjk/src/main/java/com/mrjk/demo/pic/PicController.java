package com.mrjk.demo.pic;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class PicController {

    public static final String UPLOAD_DIR="E:\\mrjk\\src\\main\\resources\\static\\";
    private Pattern pattern = Pattern.compile("(.*)\\.(.*)");

    @RequestMapping(value = "/pic_upload",method = RequestMethod.POST)
    public String uploadPic(@RequestParam(value = "file") MultipartFile file,@RequestParam(value="name")String userName){
        try {
            uploadFile(file,userName);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"sussess\":false,\"message\":\"失败\"}";
        }
        return "{\"sussess\":true,\"message\":\"成功\"}";
    }

    public void uploadFile( MultipartFile file,String userName) throws IOException {
        Matcher matcher = pattern.matcher(file.getOriginalFilename());
        matcher.find();
        File in = new File(UPLOAD_DIR + userName);
        File dest = in.getParentFile();
        if (!dest.exists()){
            dest.mkdirs();
        }
        file.transferTo(in);
    }

}
