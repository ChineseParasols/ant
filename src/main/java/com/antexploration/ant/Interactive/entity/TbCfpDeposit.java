package com.antexploration.ant.Interactive.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class TbCfpDeposit extends Model<TbCfpDeposit> {

    private static final long serialVersionUID = 1L;

    /**
     * 理财存入记录
     */
    @TableId(value = "cfp_deposit_id", type = IdType.AUTO)
    private Integer cfpDepositId;

    /**
     * 用户ID
     */
    private Integer uid;

    /**
     * 转入金额
     */
    private String deposit;

    /**
     * 转入时间
     */
    private String depositTime;

    /**
     * 标识
     */
    private String identifier;


    @Override
    protected Serializable pkVal() {
        return this.cfpDepositId;
    }

}
