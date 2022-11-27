package com.ruoyi.system.service.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.DdpayshopMapper;
import com.ruoyi.system.domain.Ddpayshop;
import com.ruoyi.system.service.IDdpayshopService;
import com.ruoyi.common.core.text.Convert;

/**
 * 多多373店铺Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-11-09
 */
@Service
public class DdpayshopServiceImpl implements IDdpayshopService 
{
    @Autowired
    private DdpayshopMapper ddpayshopMapper;


    private String shopUri = "https://newuser.dd373.com/Api/User/UserCenter/GetUserBaseInfo";

    /**
     * 查询多多373店铺
     * 
     * @param id 多多373店铺主键
     * @return 多多373店铺
     */
    @Override
    public Ddpayshop selectDdpayshopById(Long id)
    {
        return ddpayshopMapper.selectDdpayshopById(id);
    }

    /**
     * 查询多多373店铺列表
     * 
     * @param ddpayshop 多多373店铺
     * @return 多多373店铺
     */
    @Override
    public List<Ddpayshop> selectDdpayshopList(Ddpayshop ddpayshop)
    {
        return ddpayshopMapper.selectDdpayshopList(ddpayshop);
    }

    /**
     * 新增多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    @Override
    public int insertDdpayshop(Ddpayshop ddpayshop)
    {
        ddpayshop.setCreateTime(DateUtils.getNowDate());
        ddpayshop = getShopInfo(ddpayshop);
        if(StringUtils.isEmpty(ddpayshop.getAppid())){
            return 0;
        }
        return ddpayshopMapper.insertDdpayshop(ddpayshop);
    }

    /**
     * 修改多多373店铺
     * 
     * @param ddpayshop 多多373店铺
     * @return 结果
     */
    @Override
    public int updateDdpayshop(Ddpayshop ddpayshop)
    {
        ddpayshop.setUpdateTime(DateUtils.getNowDate());

        return ddpayshopMapper.updateDdpayshop(ddpayshop);
    }

    @Override
    public AjaxResult updateshop(Ddpayshop ddpayshop) {

        String appid = ddpayshop.getAppid();
        if(ddpayshop.getStatus()==0){
            ddpayshop = getShopInfo(ddpayshop);
        }
        if(ddpayshop !=null){
            if(!appid.equals(ddpayshop.getAppid())){
                return AjaxResult.error("不同手机号的Cookie");
            }
            int count = ddpayshopMapper.updateDdpayshop(ddpayshop);
            if(count>0){
                return AjaxResult.success();
            }
        }
        return AjaxResult.error("Cookie失效");
    }

    /**
     * 批量删除多多373店铺
     * 
     * @param ids 需要删除的多多373店铺主键
     * @return 结果
     */
    @Override
    public int deleteDdpayshopByIds(String ids)
    {
        return ddpayshopMapper.deleteDdpayshopByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除多多373店铺信息
     * 
     * @param id 多多373店铺主键
     * @return 结果
     */
    @Override
    public int deleteDdpayshopById(Long id)
    {
        return ddpayshopMapper.deleteDdpayshopById(id);
    }


    public Ddpayshop getShopInfo(Ddpayshop ddpayshop){
        String objJson ="";
        try{
            objJson = HttpUtils.sendGet(shopUri, null, Constants.UTF8,ddpayshop.getCookie());
        if(StringUtils.isEmpty(objJson)){
            return null;
        }
        if(StringUtils.isEmpty(objJson)){
            return null;
        }
        JSONObject jsonObject  = JSONObject.parseObject(objJson);
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("StatusData");
        if(StringUtils.isEmpty(jsonObject1)){
            return null;
        }
        String resultCode = (String) jsonObject1.get("ResultCode");
        if(!"0".equals(resultCode)){
            return null;
        }
        JSONObject resultData = (JSONObject)  jsonObject1.get("ResultData");
        String nickname = (String)  resultData.get("Nickname");
        String phoneNum = (String)  resultData.get("PhoneNum");
        ddpayshop.setName(nickname);
        ddpayshop.setAppid(phoneNum);
        return ddpayshop;
        }catch (Exception e){

        }
        return null;
    }
}
