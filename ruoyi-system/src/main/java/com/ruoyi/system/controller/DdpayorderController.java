package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.core.domain.entity.DdPayOrderApi;
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
import com.ruoyi.system.domain.Ddpayorder;
import com.ruoyi.system.service.IDdpayorderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 多多373订单Controller
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Controller
@RequestMapping("/system/ddpayorder")
public class DdpayorderController extends BaseController
{
    private String prefix = "system/ddpayorder";

    @Autowired
    private IDdpayorderService ddpayorderService;

    @RequiresPermissions("system:ddpayorder:view")
    @GetMapping()
    public String ddpayorder()
    {
        return prefix + "/ddpayorder";
    }

    /**
     * 查询多多373订单列表
     */
    @RequiresPermissions("system:ddpayorder:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Ddpayorder ddpayorder)
    {
        startPage();
        List<Ddpayorder> list = ddpayorderService.selectDdpayorderList(ddpayorder);
        return getDataTable(list);
    }

    /**
     * 导出多多373订单列表
     */
    @RequiresPermissions("system:ddpayorder:export")
    @Log(title = "多多373订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ddpayorder ddpayorder)
    {
        List<Ddpayorder> list = ddpayorderService.selectDdpayorderList(ddpayorder);
        ExcelUtil<Ddpayorder> util = new ExcelUtil<Ddpayorder>(Ddpayorder.class);
        return util.exportExcel(list, "多多373订单数据");
    }

    /**
     * 新增多多373订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存多多373订单
     */
    @RequiresPermissions("system:ddpayorder:add")
    @Log(title = "多多373订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Ddpayorder ddpayorder)
    {
        ddpayorder =  ddpayorderService.craeteOrderNo(ddpayorder);
        if(ddpayorder ==null){
            return null;
        }
        return toAjax(ddpayorderService.insertDdpayorder(ddpayorder));
    }

    /**
     * 修改多多373订单
     */
    @RequiresPermissions("system:ddpayorder:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Ddpayorder ddpayorder = ddpayorderService.selectDdpayorderById(id);
        mmap.put("ddpayorder", ddpayorder);
        return prefix + "/edit";
    }

    /**
     * 修改保存多多373订单
     */
    @RequiresPermissions("system:ddpayorder:edit")
    @Log(title = "多多373订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Ddpayorder ddpayorder)
    {
        return toAjax(ddpayorderService.updateDdpayorder(ddpayorder));
    }

    /**
     * 删除多多373订单
     */
    @RequiresPermissions("system:ddpayorder:remove")
    @Log(title = "多多373订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ddpayorderService.deleteDdpayorderByIds(ids));
    }

}
