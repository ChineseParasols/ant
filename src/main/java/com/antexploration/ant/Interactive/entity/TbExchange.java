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
public class TbExchange extends Model<TbExchange> {

    private static final long serialVersionUID = 1L;

    /**
     * 兑换记录表
     */
    @TableId(value = "exchange_id", type = IdType.AUTO)
    private Long exchangeId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 兑换币种
     */
    private String currency;

    /**
     * tsc兑换数量
     */
    private BigDecimal tnumber;

    /**
     * u到账数量
     */
    private BigDecimal unumber;

    /**
     * 兑换时间
     */
    private LocalDateTime exchangeTime;


    @Override
    protected Serializable pkVal() {
        return this.exchangeId;
    }

}
