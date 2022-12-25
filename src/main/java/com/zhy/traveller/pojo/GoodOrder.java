package com.zhy.traveller.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
 * @since 2022-11-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GoodOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 1表示车票业务，2表示住宿房间业务，3表示景点门票业务
     */
    private Integer goodType;

    /**
     * 单个商品价格
     */
    private BigDecimal price;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 购买数量
     */
    private Integer num;

    private String goodImage;

}
