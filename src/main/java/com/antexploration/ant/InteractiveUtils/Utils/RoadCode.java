package com.antexploration.ant.InteractiveUtils.Utils;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;


/**
 * @ClassNameRoadCode 双路的邀请码
 * @Description
 * @Author
 * @Date2019/10/10 14:51
 * @Version V1.0
 **/
public class RoadCode {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length){
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        for(int i=0; i<length; i++){
            sb.append(ALLCHAR.charAt(r.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }


    /**
     * 随机码生成
     * @author Mo
     * @return
     */
    public static String random2(){
        String random = "";
        /*随机数函数*/
        java.util.Random r=new java.util.Random();
        for(int i = 0;i<8;i++){
            /*生成36以内的随机数，不含36，并转化为36位*/
            random += Integer.toString(r.nextInt(36), 36);
        }
        return random;
    }


    public static void mainaa(String args []){
        Set<String> set = new HashSet<String>();
        int times = 1000000;
        for(int i = 0 ;i<times;i++){
            String random = random2();
            set.add(random);
            System.out.println(random);
        }
        System.out.println("重复了："+(times-set.size())+"次");
    }

}
