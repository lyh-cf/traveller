package com.zhy.traveller.controller;

import com.zhy.traveller.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/*
 *文件名: UploadController
 *创建者: 罗义恒
 *创建时间:2022/11/27 10:15
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

//    @Autowired
//    private UserService userService;
    //不能加static
    @Value("${traveller.path}")
    public String UPLOADS_PATH;
    @PostMapping("/image")
    //MultipartFile出的参数名应与前端保持一致
    public Response uploadImage(MultipartFile file) throws IOException {

        //获取原始文件名
        String originalFilename = file.getOriginalFilename();
        //取文件后缀
        final String fileSuffix=originalFilename.substring(originalFilename.lastIndexOf('.')+1);
        //文件名
        String fileName= UUID.randomUUID().toString().replace("-","")+'.'+fileSuffix;
        //文件写入
        File descFile=new File(UPLOADS_PATH,fileName);
        file.transferTo(descFile);
        //文件url
        String url="http://localhost:8888/static/"+fileName;
//        LambdaUpdateWrapper<User> userLambdaUpdateWrapper=new LambdaUpdateWrapper<>();
//        userLambdaUpdateWrapper.eq(User::getPkId,userId);
//        userLambdaUpdateWrapper.set(User::getHeadImage,url);
//        userService.update(null,userLambdaUpdateWrapper);
        return Response.success(url);
    }
}