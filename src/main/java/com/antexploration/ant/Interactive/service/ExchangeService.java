package com.antexploration.ant.Interactive.service;

import com.antexploration.ant.Interactive.entity.TbExchange;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeService {


    /**
     * 兑换USDT
     * @param coin
     * @return
     */
    String toExchange(Long uid, BigDecimal coin);


    /**
     * 查看兑换列表
     * @return
     */
    List<TbExchange> exchangeRecord(Long uid);
}
