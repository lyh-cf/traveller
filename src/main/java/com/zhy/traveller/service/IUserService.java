package com.zhy.traveller.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhy.traveller.pojo.User;

/*
 *文件名: UserService
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:04
 */

public interface IUserService extends IService<User> {
     User login(User user);
     boolean checkUserEmailIsExist(String userEmail);
}
