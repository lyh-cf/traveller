package com.zhy.traveller.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.UserMapper;
import com.zhy.traveller.pojo.User;
import org.springframework.stereotype.Service;

/*
 *文件名: UserServiceImpl
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:05
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User>{

    public User login(User user) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserEmail,user.getUserEmail()).eq(User::getPassWord,user.getPassWord());
        return getOne(queryWrapper);
    }

    public boolean checkUserEmailIsExist(String userEmail) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        //防止重复注册
        queryWrapper.eq(User::getUserEmail,userEmail).eq(User::getIsDeleted,0);
        User result =getOne(queryWrapper);
        if(result==null)return false;
        return true;
    }
    public void selectUserPage(IPage<User> pageParam, String userName) {
        LambdaQueryWrapper<User>queryWrapper=new LambdaQueryWrapper<>();
        //(boolean condition, boolean isAsc, R column)
        queryWrapper.eq(User::getIsDeleted,0);
        if(userName!=null&&!userName.equals(""))queryWrapper.like(User::getUserName,userName);
        baseMapper.selectPage(pageParam, queryWrapper);
    }
    public void updateUser(User user){
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUserEmail,user.getUserEmail());
        update(user,updateWrapper);
    }
    public void deleteUser(String userEmail){
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getUserEmail,userEmail);
        updateWrapper.set(User::getIsDeleted,1);
        update(updateWrapper);
    }
}
