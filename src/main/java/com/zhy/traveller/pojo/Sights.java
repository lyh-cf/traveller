package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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
public class Sights implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 景点名
     */
    private String sightsName;

    /**
     * 景点图片
     */
    private String sightsImage;

    /**
     * 所属省份
     */
    private String province;

    /**
     * 所属城市
     */
    private String city;

    /**
     * 景点介绍
     */
    private String sightBriefIntroduction;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 参观日期
     */
    private LocalDate visitDay;

    /**
     * 总数量
     */
    private Integer totalNum;

    /**
     * 现有数量
     */
    private Integer nowNum;

    /**
     * 1表示有效，0表示无效
     */
    private Integer state;

    /**
     * 1表示删除,0表示存在
     */
    @TableLogic
    private Integer isDeleted;


}
