package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OrderListMapper;
import com.ruoyi.system.domain.OrderList;
import com.ruoyi.system.service.IOrderListService;
import com.ruoyi.common.core.text.Convert;

/**
 * ordersService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Service
public class OrderListServiceImpl implements IOrderListService 
{
    @Autowired
    private OrderListMapper orderListMapper;

    /**
     * 查询orders
     * 
     * @param id orders主键
     * @return orders
     */
    @Override
    public OrderList selectOrderListById(Long id)
    {
        return orderListMapper.selectOrderListById(id);
    }

    /**
     * 查询orders列表
     * 
     * @param orderList orders
     * @return orders
     */
    @Override
    public List<OrderList> selectOrderListList(OrderList orderList)
    {
        return orderListMapper.selectOrderListList(orderList);
    }

    /**
     * 新增orders
     * 
     * @param orderList orders
     * @return 结果
     */
    @Override
    public int insertOrderList(OrderList orderList)
    {
        return orderListMapper.insertOrderList(orderList);
    }

    /**
     * 修改orders
     * 
     * @param orderList orders
     * @return 结果
     */
    @Override
    public int updateOrderList(OrderList orderList)
    {
        return orderListMapper.updateOrderList(orderList);
    }

    /**
     * 批量删除orders
     * 
     * @param ids 需要删除的orders主键
     * @return 结果
     */
    @Override
    public int deleteOrderListByIds(String ids)
    {
        return orderListMapper.deleteOrderListByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除orders信息
     * 
     * @param id orders主键
     * @return 结果
     */
    @Override
    public int deleteOrderListById(Long id)
    {
        return orderListMapper.deleteOrderListById(id);
    }
}
