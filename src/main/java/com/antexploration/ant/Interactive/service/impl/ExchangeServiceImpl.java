package com.antexploration.ant.Interactive.service.impl;

import com.antexploration.ant.Interactive.entity.TbExchange;
import com.antexploration.ant.Interactive.entity.TbUser;
import com.antexploration.ant.Interactive.mapper.TbExchangeMapper;
import com.antexploration.ant.Interactive.mapper.TbUserMapper;
import com.antexploration.ant.Interactive.service.ExchangeService;
import com.antexploration.ant.InteractiveUtils.Utils.Constants;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Resource
    private TbExchangeMapper exchangeMapper;
    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public String toExchange(Long uid,BigDecimal coin) {
        TbUser user = tbUserMapper.selectById(uid);
        if(user == null){
            return "账号有误";
        }
        //判断TSC数量
        if(user.getTbalance().compareTo(coin)<0){
            return "TSC余额不足";
        }
        //USDT兑换数量
        BigDecimal usdt = coin.multiply(Constants.TSC_exchange.divide(Constants.USDT_exchange));
        //处理余额
        user.setTbalance(user.getTbalance().subtract(coin));
        user.setUbalance(user.getUbalance().add(usdt));
        if(tbUserMapper.updateById(user) > 0){
            //添加兑换记录
            TbExchange tbExchange = new TbExchange();
            tbExchange.setUserId(uid);
            tbExchange.setCurrency("USDT");
            tbExchange.setTnumber(coin);
            tbExchange.setUnumber(usdt);
            tbExchange.setExchangeTime(LocalDateTime.now());
            if(exchangeMapper.insert(tbExchange) > 0){
                return "兑换成功";
            }
        }

        return "兑换失败";
    }

    @Override
    public List<TbExchange> exchangeRecord(Long uid) {
        QueryWrapper<TbExchange> tbExchangeQueryWrapper = new QueryWrapper<>();
        tbExchangeQueryWrapper.eq("uid",uid);
        return  exchangeMapper.selectList(tbExchangeQueryWrapper);
    }
}
