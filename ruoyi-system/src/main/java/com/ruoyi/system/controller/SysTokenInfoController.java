package com.ruoyi.system.controller;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayshopService;
import com.ruoyi.system.service.impl.DdpayorderServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ruoyi.system.domain.SysTokenInfo;
import com.ruoyi.system.service.ISysTokenInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * TokenInfoController
 * 
 * @author ruoyi
 * @date 2022-12-08
 */
@Controller
@RequestMapping("/system/tokenInfo")
public class SysTokenInfoController extends BaseController
{
    private String prefix = "system/tokenInfo";

    @Autowired
    private ISysTokenInfoService sysTokenInfoService;

    @Autowired
    private IDdpayshopService iDdpayshopService;
    private static final Logger log = LoggerFactory.getLogger(DdpayorderServiceImpl.class);

    @RequiresPermissions("system:tokenInfo:view")
    @GetMapping()
    public String TokenInfo()
    {
        return prefix + "/tokenInfo";
    }

    /**
     * 查询TokenInfo列表
     */
    @RequiresPermissions("system:tokenInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysTokenInfo sysTokenInfo)
    {
        startPage();
        List<SysTokenInfo> list = sysTokenInfoService.selectSysTokenInfoList(sysTokenInfo);
        return getDataTable(list);
    }

    /**
     * 导出TokenInfo列表
     */
    @RequiresPermissions("system:tokenInfo:export")
    @Log(title = "TokenInfo", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysTokenInfo sysTokenInfo)
    {
        List<SysTokenInfo> list = sysTokenInfoService.selectSysTokenInfoList(sysTokenInfo);
        ExcelUtil<SysTokenInfo> util = new ExcelUtil<SysTokenInfo>(SysTokenInfo.class);
        return util.exportExcel(list, "TokenInfo数据");
    }

    /**
     * 新增TokenInfo
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存TokenInfo
     */
    @RequiresPermissions("system:tokenInfo:add")
    @Log(title = "TokenInfo", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysTokenInfo sysTokenInfo)
    {
        return toAjax(sysTokenInfoService.insertSysTokenInfo(sysTokenInfo));
    }


    /**
     * 新增保存TokenInfo
     */
    @RequiresPermissions("system:tokenInfo:batchAdd")
    @Log(title = "TokenInfo", businessType = BusinessType.INSERT)
    @GetMapping("/batchAdd")
    @ResponseBody
    public AjaxResult batchAddSave(SysTokenInfo sysTokenInfo) throws InterruptedException {
        //管理员登录
        String tokenUrl = "http://h5.mall2.yingliao.tv/adminapi/login";
        List<Ddpayshop> ddpayshops = iDdpayshopService.selectDdpayshopList(new Ddpayshop());
        int count = 0;
        for (Ddpayshop shop:ddpayshops) {
            String tokenPostdata = "{\"account\":\""+shop.getClientid()+"\",\"pwd\":\""+shop.getClientsec()+"\"}";
            String objJson = HttpUtils.doHttpPost(tokenUrl,tokenPostdata,"application/json",null);
            if(StringUtils.isEmpty(objJson)){
                return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
            }
            log.info( "----------------返回值:"+objJson);
            JSONObject resultJson  = JSONObject.parseObject(objJson);
            JSONObject resultDataJson = (JSONObject) resultJson.get("data");
            String resultDataTokenJson = "Bearer "+ resultDataJson.get("token");
            for(int i=0;i<10;i++){
                StringUtils.getIdNo(true);
                String phone = StringUtils.getPhoneNum();
                String pwd = "123qwe";
                String createPostData = "{" +
                        "    \"uid\": 0," +
                        "    \"real_name\": \"1\"," +
                        "    \"phone\": \""+phone+"\"," +
                        "    \"birthday\": \"2022-12-14\"," +
                        "    \"card_id\": \""+StringUtils.getIdNo(true)+"\"," +
                        "    \"addres\": \"1\"," +
                        "    \"mark\": \"1\"," +
                        "    \"pwd\": \""+pwd+"\"," +
                        "    \"true_pwd\": \""+pwd+"\"," +
                        "    \"level\": 2," +
                        "    \"group_id\": \"\"," +
                        "    \"label_id\": []," +
                        "    \"spread_open\": 0," +
                        "    \"is_promoter\": 0," +
                        "    \"status\": 1" +
                        "}";
                log.info("Token = "+ resultDataTokenJson);
                String craeteUserUrl = "http://h5.mall2.yingliao.tv/adminapi/user/user";
                String resultcreateUser =HttpUtils.doHttpPost(craeteUserUrl,createPostData,"application/json",resultDataTokenJson);
                if(StringUtils.isEmpty(resultcreateUser)){
                    return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
                }
                log.info( "----------------返回值:"+resultcreateUser);
                JSONObject resultUser  = JSONObject.parseObject(resultcreateUser);
                String status = resultUser.get("status")+"";
                if("200".equals(status)){
                  log.info("创建成功~！ 手机号："+phone+";");
                   SysTokenInfo tokenInfo = new SysTokenInfo();
                    tokenInfo.setUsername(phone);
                    tokenInfo.setPwd(pwd);
                    tokenInfo.setShopId(shop.getAppid());
                    tokenInfo.setCreateTime(new Date());
                    tokenInfo.setUpdateTime(new Date());
                    sysTokenInfoService.insertSysTokenInfo(tokenInfo);
                    count++;
                }
                Thread.sleep(1000);
            }
        }
        return toAjax(count);
    }


    /**
     * 修改TokenInfo
     */
    @RequiresPermissions("system:tokenInfo:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        SysTokenInfo sysTokenInfo = sysTokenInfoService.selectSysTokenInfoById(id);
        mmap.put("sysTokenInfo", sysTokenInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存TokenInfo
     */
    @RequiresPermissions("system:tokenInfo:edit")
    @Log(title = "TokenInfo", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysTokenInfo sysTokenInfo)
    {
        return toAjax(sysTokenInfoService.updateSysTokenInfo(sysTokenInfo));
    }

    /**
     * 删除TokenInfo
     */
    @RequiresPermissions("system:tokenInfo:remove")
    @Log(title = "TokenInfo", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysTokenInfoService.deleteSysTokenInfoByIds(ids));
    }
}
