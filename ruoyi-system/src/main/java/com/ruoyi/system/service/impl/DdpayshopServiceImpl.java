package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DdpayshopMapper;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayshopService;
import com.ruoyi.common.core.text.Convert;

/**
 * 多多373店铺Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
@Service
public class DdpayshopServiceImpl implements IDdpayshopService 
{
    @Autowired
    private DdpayshopMapper ddpayshopMapper;

    /**
     * 查询多多373店铺
     * 
     * @param id 多多373店铺主键
     * @return 多多373店铺
     */
    @Override
    public Ddpayshop selectDdpayshopById(Long id)
    {
        return ddpayshopMapper.selectDdpayshopById(id);
    }

    /**
     * 查询多多373店铺列表
     * 
     * @param ddpayshop 多多373店铺
     * @return 多多373店铺
     */
    @Override
    public List<Ddpayshop> selectDdpayshopList(Ddpayshop ddpayshop)
    {
        return ddpayshopMapper.selectDdpayshopList(ddpayshop);
    }

    /**
     * 新增多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    @Override
    public int insertDdpayshop(Ddpayshop ddpayshop)
    {
        ddpayshop.setCreateTime(DateUtils.getNowDate());
        return ddpayshopMapper.insertDdpayshop(ddpayshop);
    }

    /**
     * 修改多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    @Override
    public int updateDdpayshop(Ddpayshop ddpayshop)
    {
        ddpayshop.setUpdateTime(DateUtils.getNowDate());
        return ddpayshopMapper.updateDdpayshop(ddpayshop);
    }

    /**
     * 批量删除多多373店铺
     * 
     * @param ids 需要删除的多多373店铺主键
     * @return 结果
     */
    @Override
    public int deleteDdpayshopByIds(String ids)
    {
        return ddpayshopMapper.deleteDdpayshopByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除多多373店铺信息
     * 
     * @param id 多多373店铺主键
     * @return 结果
     */
    @Override
    public int deleteDdpayshopById(Long id)
    {
        return ddpayshopMapper.deleteDdpayshopById(id);
    }
}
