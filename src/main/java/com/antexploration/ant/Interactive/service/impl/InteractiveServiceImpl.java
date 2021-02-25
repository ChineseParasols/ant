package com.antexploration.ant.Interactive.service.impl;

import com.antexploration.ant.Interactive.entity.*;
import com.antexploration.ant.Interactive.mapper.*;
import com.antexploration.ant.Interactive.service.InteractiveService;
import com.antexploration.ant.InteractiveUtils.Interceptor.TokenHS256;
import com.antexploration.ant.InteractiveUtils.RedisUtil.RedisService;
import com.antexploration.ant.InteractiveUtils.Utils.OSSService;
import com.antexploration.ant.InteractiveUtils.Utils.RoadCode;
import com.antexploration.ant.InteractiveUtils.Utils.TimeMonthUtile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author CJL
 * @since 2021-02-24
 */
@Service
public class InteractiveServiceImpl implements InteractiveService {

    @Resource
    RedisService redisService;

    @Resource
    TbUserMapper tbUserMapper;

    @Resource
    TbInviteMapper tbInviteMapper;

    @Resource
    OSSService ossService;

    @Resource
    TbSigninMapper tbSigninMapper;

    @Resource
    TbExchangeMapper tbExchangeMapper;



    @Override
    public String registeredUser(String phone, String code, String inviteCode) {

        boolean status = StringUtils.isNotBlank(inviteCode);
        boolean functionStatus = false;

        QueryWrapper<TbUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        List<TbUser> userList = tbUserMapper.selectList(userQueryWrapper);
        if(userList != null && !userList.isEmpty())
            return "手机号已注册";

        String phoneCode = redisService.get(2, "REIDS_SEND_Registered_CODE" + phone);
        if(!code.equals(phoneCode)){
            return "验证码有误";
        }else if(StringUtils.isBlank(phoneCode)){
            return "验证码已失效";
        }

        String id_path;
        String parent_id_path = null;
        Long parentId = null;
        //处理 parent_id_path
        if(status){
            QueryWrapper<TbUser> uqw = new QueryWrapper<>();
            uqw.eq("code",inviteCode);
            List<TbUser> invitUserList = tbUserMapper.selectList(uqw);

            if(invitUserList == null && invitUserList.isEmpty())
                return "此邀请链接已失效";

            TbUser invitUser = invitUserList.get(0);
            parentId = invitUser.getUid();
            QueryWrapper<TbInvite> inviteQW = new QueryWrapper<>();
            inviteQW.eq("uid",parentId);
            List<TbInvite> inviteList = tbInviteMapper.selectList(inviteQW);
            TbInvite inviteDate = inviteList.get(0);
            parent_id_path = inviteDate.getIdPath();
        }

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        BigDecimal zero = new BigDecimal(0);
        String sendCode = RoadCode.random2();
        String TSCAddress = "TSC" + RoadCode.generateString(32);
        Long uid;
        TbUser addUser = new TbUser(null, uuid, phone, "", "", null, TSCAddress, zero, zero, null, sendCode, 0, 30, LocalDateTime.now());
        boolean insert = addUser.insert();
        uid = addUser.getUid();

        if(insert){
            //不等于null
            if(status){
                id_path = parent_id_path + "." + uid + "";
            } else {
                id_path = uid + ".";
            }
            TbInvite addInvite = new TbInvite(null, uid, parentId, id_path, parent_id_path, null, LocalDateTime.now());
            functionStatus = addInvite.insert();
        }
        return functionStatus ? "注册成功":"操作失败";
    }

    @Override
    public String sendRegisteredCode(String phone) {
        String set = redisService.set(2, "REIDS_SEND_Registered_CODE" + phone, "5555");
        return !set.equals("OK") ? "OK":"error";
    }

    @Override
    public userSign antSing(String phone,String code) {
        String statusMgs;
        TbUser user = null;
        String token = null;

        QueryWrapper<TbUser> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("phone",phone);
        List<TbUser> userList = tbUserMapper.selectList(userQueryWrapper);
        if(userList == null || userList.isEmpty()){
            statusMgs = "手机号未注册";
        }else{
            TbUser tbUser = userList.get(0);
            user = tbUser;
            statusMgs = "登陆成功";
            token = TokenHS256.Token(tbUser.getUid() + "");
            redisService.setex(1, "REDIS_TOKEN_" + tbUser.getUid() + "", token, 86400);
        }

        String phoneCode = redisService.get(2, "REIDS_SEND_Registered_CODE" + phone);
        if(!code.equals(phoneCode)){
            statusMgs = "验证码有误";
        }else if(StringUtils.isBlank(phoneCode)){
            statusMgs =  "验证码已失效";
        }

        userSign userSign = new userSign(user, statusMgs, token);
        return userSign;
    }

    @Override
    public String sendSignCode(String phone) {
        String set = redisService.set(2, "REIDS_SEND_Sign_CODE" + phone, "5555");
        return !set.equals("OK") ? "OK":"error";
    }

    @Override
    public String upImg(Long uid, MultipartFile img) throws Exception {
        String imgUrl;
        String originalFilename = img.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        String url = "news/" + UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        ossService.uploading(img.getBytes(), url);
        imgUrl = "http://sh-web.oss-cn-beijing.aliyuncs.com/" + url;

        TbUser tbUser = new TbUser();
        tbUser.setUid(uid); tbUser.setAvatar(imgUrl);
        boolean b = tbUser.updateById();
        return b ? "操作成功":"操作失败";
    }

    @Override
    public String upName(Long uid, String name) {
        TbUser tbUser = new TbUser();
        tbUser.setUid(uid); tbUser.setNickname(name);
        boolean b = tbUser.updateById();
        return b ? "操作成功":"操作失败";
    }

    @Override
    public String SignAnt(Long uid) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String format = sdf.format(new Date());
        TbSignin tbSignin = tbSigninMapper.selectByUidToday(uid, format);
        if(tbSignin == null){
            TbSignin signin = new TbSignin(null, uid, 5, LocalDateTime.now());
            boolean insert = signin.insert();
            return insert ? "签到成功":"签到失败";
        }
        return "已签到";
    }

    @Override
    public List<SignInRecording> SignData(Long uid) throws ParseException {
        List<SignInRecording> list = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date1 = sdf.format(new Date());

        TbUser user = tbUserMapper.selectById(uid);
        //求当前位置
        String date2 = user.getRegisteredTime().format(dtf);
        int i = TimeMonthUtile.differentDays(date1, date2);
        int currentLocation = i % 7 ;

        //求左侧
        int left = currentLocation - 1;
        i = i - left;
        String leftTime = TimeMonthUtile.TheDayMoveForward(date1, left);
        for(int j = 0 ; j < 7 ; j++){
            SignInRecording signInRecording = new SignInRecording();
            left = left + 1;
            String rightTime = TimeMonthUtile.TheDayMoveback(leftTime, j);
            TbSignin tbSignin = tbSigninMapper.selectByUidToday(uid, rightTime);
            if(tbSignin == null){
                signInRecording.setStatus(0);
            }else{
                signInRecording.setStatus(1);
            }
            signInRecording.setHeaven(i + "");
            list.add(signInRecording);
            i ++;
        }
        return list;
    }

    @Override
    public String AntExplore(Long uid) {
        int explore = tbSigninMapper.AntExplore(uid);
        return explore > 0 ? "探索成功":"请补充探索能量";
    }


    public static void main(String[] args) {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
    }

}
