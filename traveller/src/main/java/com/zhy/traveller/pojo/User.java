package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
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

    //mybatis-plus会默认把id作为主键
    /*
    @TableId将属性所对应的字段指定为主键，另外Mybatis-plus默认用雪花算法生成id
    如果数据库设计id自增，那么需要在pojo的id属性加@TableId，并指定type为自增
    否则还是会默认用雪花算法生成
     */
    @TableId
    private Long id;//因为是用雪花算法生成的id，得用long接收

    private String userEmail;

    private String userName;

    private String passWord;

    private Integer sex;//性别(1表示男,2表示女)

    private String birthday;
}
