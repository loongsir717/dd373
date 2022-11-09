package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OrderList;
import com.ruoyi.system.service.IOrderListService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * ordersController
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Controller
@RequestMapping("/system/list")
public class OrderListController extends BaseController
{
    private String prefix = "system/list";

    @Autowired
    private IOrderListService orderListService;

    @RequiresPermissions("system:list:view")
    @GetMapping()
    public String list()
    {
        return prefix + "/list";
    }

    /**
     * 查询orders列表
     */
    @RequiresPermissions("system:list:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderList orderList)
    {
        startPage();
        List<OrderList> list = orderListService.selectOrderListList(orderList);
        return getDataTable(list);
    }

    /**
     * 导出orders列表
     */
    @RequiresPermissions("system:list:export")
    @Log(title = "orders", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderList orderList)
    {
        List<OrderList> list = orderListService.selectOrderListList(orderList);
        ExcelUtil<OrderList> util = new ExcelUtil<OrderList>(OrderList.class);
        return util.exportExcel(list, "orders数据");
    }

    /**
     * 新增orders
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存orders
     */
    @RequiresPermissions("system:list:add")
    @Log(title = "orders", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderList orderList)
    {
        return toAjax(orderListService.insertOrderList(orderList));
    }

    /**
     * 修改orders
     */
    @RequiresPermissions("system:list:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OrderList orderList = orderListService.selectOrderListById(id);
        mmap.put("orderList", orderList);
        return prefix + "/edit";
    }

    /**
     * 修改保存orders
     */
    @RequiresPermissions("system:list:edit")
    @Log(title = "orders", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderList orderList)
    {
        return toAjax(orderListService.updateOrderList(orderList));
    }

    /**
     * 删除orders
     */
    @RequiresPermissions("system:list:remove")
    @Log(title = "orders", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orderListService.deleteOrderListByIds(ids));
    }
}
