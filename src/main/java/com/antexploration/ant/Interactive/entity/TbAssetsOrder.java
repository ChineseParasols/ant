package com.antexploration.ant.Interactive.entity;

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
public class TbAssetsOrder extends Model<TbAssetsOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 资产流水订单id
     */
    @TableId(value = "assets_id", type = IdType.AUTO)
    private Long assetsId;

    /**
     * 用户id
     */
    private Long uid;

    /**
     * 资产类型
     */
    private String coinType;

    /**
     * 入账地址
     */
    private String fromAddress;

    /**
     * 出账地址
     */
    private String toAddress;

    /**
     * 0:入账 1:出账 2:划账
     */
    private Integer assetsType;

    /**
     * 0:未处理 1:已完成
     */
    private Integer assetsStatus;

    /**
     * 操作时间
     */
    private LocalDateTime assetsTime;


    @Override
    protected Serializable pkVal() {
        return this.assetsId;
    }

}
