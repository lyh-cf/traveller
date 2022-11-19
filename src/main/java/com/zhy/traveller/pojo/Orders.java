package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 火车ID
     */
    private Long trainId;

    /**
     * 下单用户ID
     */
    private Long userId;

    /**
     * 订单编号
     */
    private String serialNumber;

    /**
     * 下单时间
     */
    private String time;

    /**
     * 火车起始站点ID
     */
    private Long beginSite;

    /**
     * 火车终点站ID
     */
    private Long endSite;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 是否出行(0表示未支付，1表示已支付，2表示未出行，3表示已出行)
     */
    private Integer status;


}
