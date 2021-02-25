package com.antexploration.ant.Interactive.service;


import com.antexploration.ant.Interactive.entity.TbProfit;

import java.util.List;

public interface ProfitService {


    /**
     * 收益记录
     * @param uid
     * @param profitType
     * @return
     */
    List<TbProfit> selectProfit(Long uid, Integer profitType);
}
