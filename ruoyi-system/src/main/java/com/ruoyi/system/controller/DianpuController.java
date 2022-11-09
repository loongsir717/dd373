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
import com.ruoyi.system.domain.Dianpu;
import com.ruoyi.system.service.IDianpuService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店铺Controller
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Controller
@RequestMapping("/system/dianpu")
public class DianpuController extends BaseController
{
    private String prefix = "system/dianpu";

    @Autowired
    private IDianpuService dianpuService;

    @RequiresPermissions("system:dianpu:view")
    @GetMapping()
    public String dianpu()
    {
        return prefix + "/dianpu";
    }

    /**
     * 查询店铺列表
     */
    @RequiresPermissions("system:dianpu:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Dianpu dianpu)
    {
        startPage();
        List<Dianpu> list = dianpuService.selectDianpuList(dianpu);
        return getDataTable(list);
    }

    /**
     * 导出店铺列表
     */
    @RequiresPermissions("system:dianpu:export")
    @Log(title = "店铺", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Dianpu dianpu)
    {
        List<Dianpu> list = dianpuService.selectDianpuList(dianpu);
        ExcelUtil<Dianpu> util = new ExcelUtil<Dianpu>(Dianpu.class);
        return util.exportExcel(list, "店铺数据");
    }

    /**
     * 新增店铺
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存店铺
     */
    @RequiresPermissions("system:dianpu:add")
    @Log(title = "店铺", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Dianpu dianpu)
    {
        return toAjax(dianpuService.insertDianpu(dianpu));
    }

    /**
     * 修改店铺
     */
    @RequiresPermissions("system:dianpu:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Dianpu dianpu = dianpuService.selectDianpuById(id);
        mmap.put("dianpu", dianpu);
        return prefix + "/edit";
    }

    /**
     * 修改保存店铺
     */
    @RequiresPermissions("system:dianpu:edit")
    @Log(title = "店铺", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Dianpu dianpu)
    {
        return toAjax(dianpuService.updateDianpu(dianpu));
    }

    /**
     * 删除店铺
     */
    @RequiresPermissions("system:dianpu:remove")
    @Log(title = "店铺", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(dianpuService.deleteDianpuByIds(ids));
    }
}
