package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Ddpayorder;

/**
 * 多多373订单Mapper接口
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
public interface DdpayorderMapper 
{
    /**
     * 查询多多373订单
     * 
     * @param id 多多373订单主键
     * @return 多多373订单
     */
    public Ddpayorder selectDdpayorderById(Long id);

    /**
     * 查询多多373订单列表
     * 
     * @param ddpayorder 多多373订单
     * @return 多多373订单集合
     */
    public List<Ddpayorder> selectDdpayorderList(Ddpayorder ddpayorder);

    /**
     * 新增多多373订单
     * 
     * @param ddpayorder 多多373订单
     * @return 结果
     */
    public int insertDdpayorder(Ddpayorder ddpayorder);

    /**
     * 修改多多373订单
     * 
     * @param ddpayorder 多多373订单
     * @return 结果
     */
    public int updateDdpayorder(Ddpayorder ddpayorder);

    /**
     * 删除多多373订单
     * 
     * @param id 多多373订单主键
     * @return 结果
     */
    public int deleteDdpayorderById(Long id);

    /**
     * 批量删除多多373订单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDdpayorderByIds(String[] ids);
}
