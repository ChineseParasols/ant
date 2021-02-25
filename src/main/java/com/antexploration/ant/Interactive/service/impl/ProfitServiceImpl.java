package com.antexploration.ant.Interactive.service.impl;

import com.antexploration.ant.Interactive.entity.TbProfit;
import com.antexploration.ant.Interactive.mapper.TbProfitMapper;
import com.antexploration.ant.Interactive.service.ProfitService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProfitServiceImpl implements ProfitService {

    @Resource
    private TbProfitMapper tbProfitMapper;

    @Override
    public List<TbProfit> selectProfit(Long uid, Integer profitType) {
        QueryWrapper<TbProfit> tbProfitQueryWrapper = new QueryWrapper<>();
        tbProfitQueryWrapper.eq("uid",uid);
        tbProfitQueryWrapper.eq("profit_type",profitType);
        return tbProfitMapper.selectList(tbProfitQueryWrapper);
    }
}
