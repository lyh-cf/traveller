package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/*
 *文件名: User
 *创建者: 罗义恒
 *创建时间:2022/10/30 21:55
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {
    private static final long serialVersionUID=1L;


    //mybatis-plus会默认把id作为主键
    /*
    @TableId将属性所对应的字段指定为主键，另外Mybatis-plus默认用雪花算法生成id
    如果数据库设计id自增，那么需要在pojo的id属性加@TableId，并指定type为自增
    否则还是会默认用雪花算法生成
     */
    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
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
     * 登录密码
     */
    private String passWord;

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
}
