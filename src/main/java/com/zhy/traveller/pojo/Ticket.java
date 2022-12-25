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
import java.time.LocalTime;

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
public class Ticket implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    //发送给前端前转成String类型
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 车次
     */
    private String trips;

    /**
     * 出发站名
     */
    private String startSite;

    /**
     * 终点站名
     */
    private String arriveSite;

    /**
     * 出发日期
     */
    private LocalDate startDay;

    /**
     * 出发时间
     */
    private LocalTime startTime;

    /**
     * 到达日期
     */
    private LocalDate arriveDay;

    /**
     * 到达时间
     */
    private LocalTime arriveTime;

    /**
     * 运行时间(单位分钟)
     */
    private Integer useTime;

    /**
     * 总票量
     */
    private Integer totalNum;

    /**
     * 剩余票量
     */
    private Integer nowNum;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 1表示有效,0表示无效
     */
    private Integer state;

    /**
     * 1表示删除,0表示存在
     */
    @TableLogic
    private Integer isDeleted;
}
