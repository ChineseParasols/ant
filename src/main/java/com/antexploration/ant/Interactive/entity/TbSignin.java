package com.antexploration.ant.Interactive.entity;

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
public class TbSignin extends Model<TbSignin> {

    private static final long serialVersionUID = 1L;

    /**
     * 签到表
     */
    @TableId(value = "signin_id", type = IdType.AUTO)
    private Long signinId;

    /**
     * uid
     */
    private Long uid;

    /**
     * 点击次数
     */
    private Integer clickonNumber;

    /**
     * 签到时间
     */
    private LocalDateTime signinTime;


    @Override
    protected Serializable pkVal() {
        return this.signinId;
    }

}
