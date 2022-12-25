package com.zhy.traveller.converter;

import com.zhy.traveller.dto.UserDTO;
import com.zhy.traveller.pojo.User;
import com.zhy.traveller.vo.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/*
 *文件名: UserMapper
 *创建者: 罗义恒
 *描述:
 *创建时间:2022/12/6 20:16
 */
@Mapper
public interface UserMapper {
    UserMapper INSTANCT = Mappers.getMapper(UserMapper.class);
    //返回的是目标对象
    User conver(UserDTO userDTO);
    UserInfo conver(User user);
}
