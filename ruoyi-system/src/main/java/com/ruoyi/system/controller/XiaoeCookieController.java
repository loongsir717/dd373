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
import com.ruoyi.system.domain.XiaoeCookie;
import com.ruoyi.system.service.IXiaoeCookieService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * cookieController
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Controller
@RequestMapping("/system/cookie")
public class XiaoeCookieController extends BaseController
{
    private String prefix = "system/cookie";

    @Autowired
    private IXiaoeCookieService xiaoeCookieService;

    @RequiresPermissions("system:cookie:view")
    @GetMapping()
    public String cookie()
    {
        return prefix + "/cookie";
    }

    /**
     * 查询cookie列表
     */
    @RequiresPermissions("system:cookie:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XiaoeCookie xiaoeCookie)
    {
        startPage();
        List<XiaoeCookie> list = xiaoeCookieService.selectXiaoeCookieList(xiaoeCookie);
        return getDataTable(list);
    }

    /**
     * 导出cookie列表
     */
    @RequiresPermissions("system:cookie:export")
    @Log(title = "cookie", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XiaoeCookie xiaoeCookie)
    {
        List<XiaoeCookie> list = xiaoeCookieService.selectXiaoeCookieList(xiaoeCookie);
        ExcelUtil<XiaoeCookie> util = new ExcelUtil<XiaoeCookie>(XiaoeCookie.class);
        return util.exportExcel(list, "cookie数据");
    }

    /**
     * 新增cookie
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存cookie
     */
    @RequiresPermissions("system:cookie:add")
    @Log(title = "cookie", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XiaoeCookie xiaoeCookie)
    {
        return toAjax(xiaoeCookieService.insertXiaoeCookie(xiaoeCookie));
    }

    /**
     * 修改cookie
     */
    @RequiresPermissions("system:cookie:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        XiaoeCookie xiaoeCookie = xiaoeCookieService.selectXiaoeCookieById(id);
        mmap.put("xiaoeCookie", xiaoeCookie);
        return prefix + "/edit";
    }

    /**
     * 修改保存cookie
     */
    @RequiresPermissions("system:cookie:edit")
    @Log(title = "cookie", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XiaoeCookie xiaoeCookie)
    {
        return toAjax(xiaoeCookieService.updateXiaoeCookie(xiaoeCookie));
    }

    /**
     * 删除cookie
     */
    @RequiresPermissions("system:cookie:remove")
    @Log(title = "cookie", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(xiaoeCookieService.deleteXiaoeCookieByIds(ids));
    }
}
