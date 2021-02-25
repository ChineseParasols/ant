package com.antexploration.ant.Interactive.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author CJL
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TbBuy extends Model<TbBuy> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "by_id", type = IdType.AUTO)
    private Long byId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 购买类型
     */
    private String commodityType;

    /**
     * 购买数量
     */
    private Integer buyNumber;

    /**
     * 购买金额
     */
    private BigDecimal buyAmount;

    /**
     * 购买时间
     */
    private LocalDateTime butTime;


    @Override
    protected Serializable pkVal() {
        return this.byId;
    }

}
