package com.antexploration.ant.Interactive.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class TbUser extends Model<TbUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.AUTO)
    private Long uid;

    /**
     * UUID
     */
    private String uuid;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * tron地址
     */
    private String waddress;

    /**
     * tsc地址
     */
    private String tscaddress;

    /**
     * U余额
     */
    private BigDecimal ubalance;

    /**
     * tsc余额
     */
    private BigDecimal tbalance;

    /**
     * M等级
     */
    private String grade;

    /**
     * 邀请码
     */
    private String code;

    /**
     * 零撸周期
     */
    private Integer cycle;

    /**
     * 0:正常 1:禁止登陆 2:禁止提币
     */
    private Integer operatingStatus;

    /**
     * 注册时间
     */
    private LocalDateTime registeredTime;


    @Override
    protected Serializable pkVal() {
        return this.uid;
    }

}
