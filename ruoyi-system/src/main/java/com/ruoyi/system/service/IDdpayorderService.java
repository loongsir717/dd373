package com.ruoyi.system.service;

import java.text.ParseException;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.DdPayOrderApi;
import com.ruoyi.system.domain.Ddpayorder;

/**
 * 多多373订单Service接口
 *
 * @author ruoyi
 * @date 2022-11-09
 */
public interface IDdpayorderService
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
     * 批量删除多多373订单
     *
     * @param ids 需要删除的多多373订单主键集合
     * @return 结果
     */
    public int deleteDdpayorderByIds(String ids);

    /**
     * 删除多多373订单信息
     *
     * @param id 多多373订单主键
     * @return 结果
     */
    public int deleteDdpayorderById(Long id);

    /**
     * 获取订单号、获取支付链接
     * @return
     */
    public AjaxResult craeteOrderNo(Ddpayorder ddpayorder);


    /**
     * 根据订单号查询充值是否成功，成功后回调
     * @return
     */
    public String callbackOrder(Ddpayorder ddpayorder) ;



    /**
     * 查询多多373订单
     *
     * @param ddpayorder 多多373订单主键
     * @return 多多373订单
     */
    public Ddpayorder queryOrder(Ddpayorder ddpayorder);




}
