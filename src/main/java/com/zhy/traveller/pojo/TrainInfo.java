package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 罗义恒
 * @since 2022-11-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class TrainInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 列车ID
     */
    private Long trainId;

    /**
     * 现有的硬座数
     */
    private Integer nowHardSeat;

    /**
     * 现有的硬卧数
     */
    private Integer nowHardSleeper;

    /**
     * 现有的软卧数
     */
    private Integer nowSoftSleeper;

    /**
     * 出发日期
     */
    private String startDay;

    /**
     * 火车出发时刻
     */
    private String startTime;

    /**
     * 到达日期
     */
    private String arriveDay;

    /**
     * 火车到达时刻
     */
    private String arriveTime;

    /**
     * 规定付款时间(分钟)
     */
    private Integer payTimes;

    /**
     * 1表示被删除,0表示存在
     */
    @TableLogic
    private Integer isDeleted;


}
