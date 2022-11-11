package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.service.IDdpayorderService;
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
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayshopService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 多多373店铺Controller
 *
 * @author ruoyi
 * @date 2022-11-09
 */
@Controller
@RequestMapping("/system/ddpayshop")
public class DdpayshopController extends BaseController
{
    private String prefix = "system/ddpayshop";

    @Autowired
    private IDdpayshopService ddpayshopService;

    @RequiresPermissions("system:ddpayshop:view")
    @GetMapping()
    public String ddpayshop()
    {
        return prefix + "/ddpayshop";
    }

    /**
     * 查询多多373店铺列表
     */
    @RequiresPermissions("system:ddpayshop:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Ddpayshop ddpayshop)
    {
        startPage();
        List<Ddpayshop> list = ddpayshopService.selectDdpayshopList(ddpayshop);
        return getDataTable(list);
    }

    /**
     * 导出多多373店铺列表
     */
    @RequiresPermissions("system:ddpayshop:export")
    @Log(title = "多多373店铺", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Ddpayshop ddpayshop)
    {
        List<Ddpayshop> list = ddpayshopService.selectDdpayshopList(ddpayshop);
        ExcelUtil<Ddpayshop> util = new ExcelUtil<Ddpayshop>(Ddpayshop.class);
        return util.exportExcel(list, "多多373店铺数据");
    }

    /**
     * 新增多多373店铺
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存多多373店铺
     */
    @RequiresPermissions("system:ddpayshop:add")
    @Log(title = "多多373店铺", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Ddpayshop ddpayshop)
    {
        ddpayshop.setStatus(0);
        ddpayshop.setPayMethod("0");
        return toAjax(ddpayshopService.insertDdpayshop(ddpayshop));
    }

    /**
     * 修改多多373店铺
     */
    @RequiresPermissions("system:ddpayshop:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Ddpayshop ddpayshop = ddpayshopService.selectDdpayshopById(id);
        mmap.put("ddpayshop", ddpayshop);
        return prefix + "/edit";
    }

    /**
     * 修改保存多多373店铺
     */

    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Ddpayshop ddpayshop)
    {
        return toAjax(ddpayshopService.updateDdpayshop(ddpayshop));
    }

    /**
     * 删除多多373店铺
     */
    @RequiresPermissions("system:ddpayshop:remove")
    @Log(title = "多多373店铺", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ddpayshopService.deleteDdpayshopByIds(ids));
    }





}
