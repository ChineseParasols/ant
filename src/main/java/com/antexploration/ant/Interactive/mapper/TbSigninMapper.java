package com.antexploration.ant.Interactive.mapper;

import com.antexploration.ant.Interactive.entity.TbSignin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CJL
 * @since 2021-02-25
 */
@Mapper
public interface TbSigninMapper extends BaseMapper<TbSignin> {

    @Select("SELECT * FROM tb_signin WHERE uid = #{uid} AND signin_time LIKE CONCAT('%',#{date},'%') ")
    TbSignin selectByUidToday(@Param("uid") Long uid,@Param("date") String date);

    @Update("UPDATE tb_signin SET clickon_number = clickon_number - 1 WHERE uid = #{uid} AND clickon_number >= 1")
    int AntExplore(@Param("uid") Long uid);
}
