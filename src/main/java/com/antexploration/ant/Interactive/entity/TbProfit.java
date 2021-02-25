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
public class TbProfit extends Model<TbProfit> {

    private static final long serialVersionUID = 1L;

    /**
     * 理财收益记录表
     */
    @TableId(value = "income_id", type = IdType.AUTO)
    private Integer incomeId;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 收益金额
     */
    private BigDecimal profitId;

    /**
     * 收益单位
     */
    private String profitUnit;

    /**
     * 0:签到 1:挖矿 2:直推销售返佣 3:挖矿返佣 4:晋级奖励 5:理财收益
     */
    private Integer profitType;

    /**
     * 结算时间
     */
    private LocalDateTime recordingTime;


    @Override
    protected Serializable pkVal() {
        return this.incomeId;
    }

}
