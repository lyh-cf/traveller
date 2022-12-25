package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 下单用户id
     */
    private Long userId;

    /**
     * 订单编号
     */
    private String serialNumber;

    /**
     * 1表示车票业务，2表示住宿房间业务，3表示景点门票业务
     */
    private Integer goodType;
    /**
     * 1表示微信，2表示支付宝
     */
    private Integer payType;

    /**
     * 下单时间
     */
    private LocalDateTime time;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 0表示未支付，1表示未出行，2表示已完成
     */
    private Integer state;


}
