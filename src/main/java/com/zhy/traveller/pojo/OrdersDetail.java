package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class OrdersDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 乘车人信息ID
     */
    private Long passengerId;

    /**
     * 座位号
     */
    private String seatSerial;

    /**
     * 座位类型(1表示硬座2表示硬卧,3表示软卧)
     */
    private Integer seatType;

    /**
     * 1表示未出行,2表示已出行
     */
    private Integer status;


}
