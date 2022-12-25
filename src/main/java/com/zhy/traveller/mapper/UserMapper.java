package com.zhy.traveller.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhy.traveller.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/*
 *文件名: UserMapper
 *创建者: 罗义恒
 *创建时间:2022/10/30 22:02
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
       /*
       BaseMapper里的添加操作会返回主键
        */
}
