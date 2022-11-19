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
public class Sites implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "pk_id", type = IdType.AUTO)
    private Long pkId;

    /**
     * 站点名称
     */
    private String site;

    /**
     * 建立时间
     */
    private String insertTime;

    /**
     * 站点所属省份
     */
    private String province;

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
