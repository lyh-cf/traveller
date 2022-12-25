package com.zhy.traveller.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/*
 *文件名: UserInfo
 *创建者: 罗义恒
 *描述:
 *创建时间:2022/12/4 0:36
 */
@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID=1L;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pkId;
    /**
     * 邮箱
     */
    private String userEmail;

    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像
     */
    private String headImage;

    /**
     * 性别(1表示男,2表示女)
     */
    private Integer sex;

    /**
     * 生日
     */
    private String birthday;

    private String token;
}
