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
public class TbInvite extends Model<TbInvite> {

    private static final long serialVersionUID = 1L;

    /**
     * 邀请表
     */
    @TableId(value = "invite_id", type = IdType.AUTO)
    private Long inviteId;

    /**
     * 名称
     */
    private Long uid;

    /**
     * 上级
     */
    private Long parentId;

    /**
     * 从根节点到本节点的所有id
     */
    private String idPath;

    /**
     * 参考id_path，不包括当前id
     */
    private String parentIdPath;

    /**
     * 对应id_path的分类名称路径
     */
    private String namePath;

    /**
     * 邀请时间
     */
    private LocalDateTime inviteTime;


    @Override
    protected Serializable pkVal() {
        return this.inviteId;
    }

}
