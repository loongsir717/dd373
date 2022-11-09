package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Ddpayshop;

/**
 * 多多373店铺Service接口
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
public interface IDdpayshopService 
{
    /**
     * 查询多多373店铺
     * 
     * @param id 多多373店铺主键
     * @return 多多373店铺
     */
    public Ddpayshop selectDdpayshopById(Long id);

    /**
     * 查询多多373店铺列表
     * 
     * @param ddpayshop 多多373店铺
     * @return 多多373店铺集合
     */
    public List<Ddpayshop> selectDdpayshopList(Ddpayshop ddpayshop);

    /**
     * 新增多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    public int insertDdpayshop(Ddpayshop ddpayshop);

    /**
     * 修改多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    public int updateDdpayshop(Ddpayshop ddpayshop);

    /**
     * 批量删除多多373店铺
     * 
     * @param ids 需要删除的多多373店铺主键集合
     * @return 结果
     */
    public int deleteDdpayshopByIds(String ids);

    /**
     * 删除多多373店铺信息
     * 
     * @param id 多多373店铺主键
     * @return 结果
     */
    public int deleteDdpayshopById(Long id);
}
