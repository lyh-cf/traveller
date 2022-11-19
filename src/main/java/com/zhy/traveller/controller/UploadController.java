package com.zhy.traveller.controller;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.zhy.traveller.common.CodeMsgEnum;
import com.zhy.traveller.common.Response;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.IUserService;
import com.zhy.traveller.utils.GetCookieValue;
import com.zhy.traveller.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 *创建时间:2022/11/15 20:22
 */
@RestController
@RequestMapping("/upload")
@Slf4j
public class UploadController {

    @Autowired
    private IUserService IUserService;

    public static final String UPLOADS_PATH="D:\\upload\\headImage\\";
    @PostMapping("/image")
    //MultipartFile出的参数名应与前端保持一致
    public Response uploadHeadImage(MultipartFile file, ServletRequest servletRequest) throws IOException {

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
        String url="http://192.168.50.243:8888/static/"+fileName;
        String token = GetCookieValue.getValue(servletRequest, "token");
        Long userId= JwtUtil.getUserId(token);
        LambdaUpdateWrapper<User> userLambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        userLambdaUpdateWrapper.eq(User::getUserEmail,userId);
        userLambdaUpdateWrapper.set(User::getHeadImage,url);
        IUserService.update(null,userLambdaUpdateWrapper);
        return Response.success(CodeMsgEnum.CHANGE_HEADIMAGE_SUCCESS,url);
    }
}
