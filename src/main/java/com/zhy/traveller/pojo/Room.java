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
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "pk_id", type = IdType.ASSIGN_ID)
    private Long pkId;

    /**
     * 旅馆ID
     */
    private Long hotelId;

    /**
     * 房间类型名称
     */
    private String typeName;

    /**
     * 房间图片
     */
    private String roomImage;

    /**
     * 总数量
     */
    private Integer totalNum;

    /**
     * 房间简介
     */
    private String roomBriefIntroduction;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 1表示有效,0表示无效
     */
    private Integer state;


}
