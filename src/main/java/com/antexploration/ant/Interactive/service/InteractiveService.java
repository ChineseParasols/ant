package com.antexploration.ant.Interactive.service;

import com.antexploration.ant.Interactive.entity.SignInRecording;
import com.antexploration.ant.Interactive.entity.TbAssetsOrder;
import com.antexploration.ant.Interactive.entity.userSign;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author CJL
 * @since 2021-02-24
 */
public interface InteractiveService  {

    /**
     * 注册用户
     * @param phone 手机号
     * @param code 验证码
     * @param inviteCode 邀请码
     * @return
     */
    String registeredUser(String phone,String code,String inviteCode);

    /**
     * 发送注册验证码
     * @param phone 手机号
     * @return
     */
    String sendRegisteredCode(String phone);

    /**
     * 登陆ant
     * @param phone 手机号
     * @return
     */
    userSign antSing(String phone,String code);

    /**
     * 发送登陆验证码
     * @param phone 手机号
     * @return
     */
    String sendSignCode(String phone);

    /**
     * 修改用户头像
     * @param uid uid
     * @param img 图片
     * @return
     */
    String upImg(Long uid,MultipartFile img) throws Exception;

    /**
     * 修改用户名
     * @param uid uid
     * @param name 用户名
     * @return
     */
    String upName(Long uid,String name);

    /**
     * Ant 签到
     * @param uid
     * @return
     */
    String SignAnt(Long uid);

    /**
     * 查询签到天数
     * @param uid uid
     * @return
     */
    List<SignInRecording> SignData(Long uid) throws ParseException;

    /**
     * 点击探索
     * @param uid uid
     * @return
     */
    String AntExplore(Long uid);
}
