package com.zhy.traveller.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.UserMapper;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.IUserService;

import org.springframework.stereotype.Service;

/*
 *文件名: UserServiceImpl
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:05
 */
@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(User user) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserEmail,user.getUserEmail()).eq(User::getPassWord,user.getPassWord());
        return getOne(queryWrapper);
    }

    @Override
    public boolean checkUserEmailIsExist(String userEmail) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        //防止重复注册
        queryWrapper.eq(User::getUserEmail,userEmail);
        User result =getOne(queryWrapper);
        if(result==null)return false;
        return true;
    }
}
