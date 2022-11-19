package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class Train implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 车次
     */
    private String trainSerialNumber;

    /**
     * 用时
     */
    private String useTime;

    /**
     * 起始站点ID
     */
    private Long beginSite;

    /**
     * 终点站ID
     */
    private Long endSite;

    /**
     * 总硬座数
     */
    private Integer totalHardSeat;

    /**
     * 总硬卧数
     */
    private Integer totalHardSleeper;

    /**
     * 总软卧数
     */
    private Integer totalSoftSleeper;

    /**
     * 硬座价格
     */
    private BigDecimal hardSeatPrice;

    /**
     * 硬卧价格
     */
    private BigDecimal hardSleeperPrice;

    /**
     * 软卧价格
     */
    private BigDecimal softSleeperPrice;

    /**
     * 1表示正在使用,0表示停用
     */
    private Integer isUse;

    /**
     * 1表示被删除,0表示存在
     */
    @TableLogic
    private Integer isDeleted;


}
