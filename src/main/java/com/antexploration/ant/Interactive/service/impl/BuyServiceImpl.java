package com.antexploration.ant.Interactive.service.impl;

import com.antexploration.ant.Interactive.entity.TbBuy;
import com.antexploration.ant.Interactive.mapper.TbBuyMapper;
import com.antexploration.ant.Interactive.mapper.TbUserMapper;
import com.antexploration.ant.Interactive.service.BuyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BuyServiceImpl implements BuyService {

    @Resource
    private TbUserMapper tbUserMapper;
    @Resource
    private TbBuyMapper tbBuyMapper;

    @Override
    public List<TbBuy> buyRecord(Long uid) {
        QueryWrapper<TbBuy> tbBuyQueryWrapper = new QueryWrapper<>();
        tbBuyQueryWrapper.eq("uid",uid);
        return tbBuyMapper.selectList(tbBuyQueryWrapper);
    }
}
