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
public class TrainSite implements Serializable {

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
     * 站点ID
     */
    private Long siteId;

    /**
     * 停靠时间
     */
    private String stopTime;

    /**
     * 到达时间
     */
    private String arriveTime;

    /**
     * 出发时间
     */
    private String startTime;

    /**
     * 1表示被删除,0表示存在
     */
    @TableLogic
    private Integer isDeleted;


}
