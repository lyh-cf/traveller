package com.zhy.traveller.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhy.traveller.mapper.UserMapper;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.service.UserService;

import org.springframework.stereotype.Service;

/*
 *文件名: UserServiceImpl
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
