package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 罗义恒
 * @since 2022-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Hotel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 旅馆名
     */
    private String hotelName;
    /**
     * 旅馆图片
     */
    private String hotelImage;

    /**
     * 店家主人邮箱
     */
    private String hotelMasterEmail;

    /**
     * 所属省份
     */
    private String province;
    /**
     * 所属城市
     */
    private String city;

    /**
     * 旅馆详细位置
     */
    private String hotelAddress;

    /**
     * 旅馆简介
     */
    private String hotelBriefIntroduction;

    /**
     * 1表示删除,0表示上线
     */
    @TableLogic
    private Integer isDeleted;


}
