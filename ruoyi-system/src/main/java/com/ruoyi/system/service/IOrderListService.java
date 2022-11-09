package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.OrderList;

/**
 * ordersService接口
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public interface IOrderListService 
{
    /**
     * 查询orders
     * 
     * @param id orders主键
     * @return orders
     */
    public OrderList selectOrderListById(Long id);

    /**
     * 查询orders列表
     * 
     * @param orderList orders
     * @return orders集合
     */
    public List<OrderList> selectOrderListList(OrderList orderList);

    /**
     * 新增orders
     * 
     * @param orderList orders
     * @return 结果
     */
    public int insertOrderList(OrderList orderList);

    /**
     * 修改orders
     * 
     * @param orderList orders
     * @return 结果
     */
    public int updateOrderList(OrderList orderList);

    /**
     * 批量删除orders
     * 
     * @param ids 需要删除的orders主键集合
     * @return 结果
     */
    public int deleteOrderListByIds(String ids);

    /**
     * 删除orders信息
     * 
     * @param id orders主键
     * @return 结果
     */
    public int deleteOrderListById(Long id);
}
