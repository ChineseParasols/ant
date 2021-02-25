package com.antexploration.ant.InteractiveUtils.Interceptor;


import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.nimbusds.jose.JOSEException;

import net.minidev.json.JSONObject;

public class TokenHS256 {

    /**
     * 生成token的业务逻辑
     * 这个方法采取的是HS256 对称加密算法
     *
     * @param uid
     * @return
     */
    public static String Token(String uid) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", uid);
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime() + 600000);
        try {
            String token = TokenUtils.creatTokenHS256(map);
            System.out.println("token=" + token);
            return token;
        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 处理解析的业务逻辑
     *
     * @param token token
     * @return
     */
    public static Map<String, Object> ValidToken(String token) {
        Map<String, Object> map = new HashMap<>();
        //解析token
        try {
            if (token != null) {
                Map<String, Object> validMap = TokenUtils.validHS256(token);
                int i = Integer.parseInt(validMap.get("Result").toString());
                JSONObject jsonObject = (JSONObject) validMap.get("data");
                map.put("uid", jsonObject.get("uid") + "");
                if (i == 0) {
                    map.put("status", 0);
                } else if (i == 1) {
                    map.put("status", 1);
                } else {
                    map.put("status", 2);
                }
            }
            return map;
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        map.put("status", 2);
        return map;
    }

    public static void mainaa(String[] args) {
        //获取生成token
        Map<String, Object> map = new HashMap<>();

        //建立载荷，这些数据根据业务，自己定义。
        map.put("uid", "1");
        //生成时间
        map.put("sta", new Date().getTime());
        //过期时间
        map.put("exp", new Date().getTime() + 259200000000000000l);
        try {
            String token = TokenUtils.creatTokenHS256(map);
            System.out.println("token=" + token);

        } catch (JOSEException e) {
            System.out.println("生成token失败");
            e.printStackTrace();
        }
    }

    public static void main2(String[] ages) {
        // token为三部分分根据.
        //第部分为为请头头
        //第部分分为消息的载体 （也就是可把把消息存在这里）
        //第三部分为签名
        //获取token
        String uid = "kkksuejrmf";
        String token = Token(uid);
        //解析token
        ValidToken(token);
    }
}
