package com.zhy.traveller.controller.dto;

import com.zhy.traveller.pojo.User;
import lombok.Data;


/*
 *文件名: UserMsg
 *创建者: 罗义恒
 *创建时间:2022/11/8 1:01
 */
@Data
public class UserMsg extends User {
    private Integer code;
}
