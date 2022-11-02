package com.zhy.traveller.pojo;

import lombok.Data;

import java.io.Serializable;

/*
 *文件名: User
 *创建者: 罗义恒
 *创建时间:2022/10/30 21:55
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    private int id;

    private String userEmail;

    private String userName;

    private String passWord;

    private int sex;//性别(1表示男,2表示女)

    private String birthday;
}
