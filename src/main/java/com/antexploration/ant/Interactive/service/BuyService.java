package com.antexploration.ant.Interactive.service;

import com.antexploration.ant.Interactive.entity.TbBuy;

import java.util.List;

public interface BuyService {


    /**
     * 购买记录
     * @param uid
     * @return
     */
    List<TbBuy> buyRecord(Long uid);
}
