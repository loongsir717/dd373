package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.controller.SysTokenInfoController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysTokenInfoMapper;
import com.ruoyi.system.domain.SysTokenInfo;
import com.ruoyi.system.service.ISysTokenInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * TokenInfoService业务层处理
 *
 * @author ruoyi
 * @date 2022-12-08
 */
@Service
public class SysTokenInfoServiceImpl implements ISysTokenInfoService
{
    @Autowired
    private SysTokenInfoMapper sysTokenInfoMapper;

    private static final Logger log = LoggerFactory.getLogger(ISysTokenInfoService.class);
    /**
     * 查询TokenInfo
     *
     * @param id TokenInfo主键
     * @return TokenInfo
     */
    @Override
    public SysTokenInfo selectSysTokenInfoById(Long id)
    {
        return sysTokenInfoMapper.selectSysTokenInfoById(id);
    }

    /**
     * 查询TokenInfo列表
     *
     * @param sysTokenInfo TokenInfo
     * @return TokenInfo
     */
    @Override
    public List<SysTokenInfo> selectSysTokenInfoList(SysTokenInfo sysTokenInfo)
    {
        return sysTokenInfoMapper.selectSysTokenInfoList(sysTokenInfo);
    }

    /**
     * 新增TokenInfo
     *
     * @param sysTokenInfo TokenInfo
     * @return 结果
     */
    @Override
    public int insertSysTokenInfo(SysTokenInfo sysTokenInfo)
    {
        sysTokenInfo.setCreateTime(DateUtils.getNowDate());
        return sysTokenInfoMapper.insertSysTokenInfo(sysTokenInfo);
    }

    /**
     * 修改TokenInfo
     *
     * @param sysTokenInfo TokenInfo
     * @return 结果
     */
    @Override
    public int updateSysTokenInfo(SysTokenInfo sysTokenInfo)
    {
        sysTokenInfo.setUpdateTime(DateUtils.getNowDate());
        return sysTokenInfoMapper.updateSysTokenInfo(sysTokenInfo);
    }

    /**
     * 批量删除TokenInfo
     *
     * @param ids 需要删除的TokenInfo主键
     * @return 结果
     */
    @Override
    public int deleteSysTokenInfoByIds(String ids)
    {
        return sysTokenInfoMapper.deleteSysTokenInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除TokenInfo信息
     *
     * @param id TokenInfo主键
     * @return 结果
     */
    @Override
    public int deleteSysTokenInfoById(Long id)
    {
        return sysTokenInfoMapper.deleteSysTokenInfoById(id);
    }


    @Override
    public AjaxResult updateTokeninfo() {
        //查询账号
        List<SysTokenInfo> sysTokenInfos = sysTokenInfoMapper.selectSysTokenInfoList(new SysTokenInfo());
        String loginUrl = "http://h5.mall2.yingliao.tv/api/login";
        int count = 0;
        for(SysTokenInfo sysTokenInfo:sysTokenInfos){
            //登录账号
            String postDate = " {\"account\":\""+sysTokenInfo.getUsername()+"\",\"password\":\""+sysTokenInfo.getPwd()+"\"}";
            String objJson = HttpUtils.doHttpPost(loginUrl,postDate,"application/json",null);
            if(StringUtils.isEmpty(objJson)){
                return new AjaxResult(AjaxResult.Type.ERROR,"调用失败",null);
            }
            log.info( "----------------登錄返回值:"+objJson);
            JSONObject resultJson  = JSONObject.parseObject(objJson);
            JSONObject resultDataJson = (JSONObject) resultJson.get("data");
            String cookie = "Bearer "+ resultDataJson.get("token");
            sysTokenInfo.setCookie(cookie);
            sysTokenInfo.setUpdateTime(new Date());
            //更新token
            int upCount = sysTokenInfoMapper.updateSysTokenInfo(sysTokenInfo);
            if(upCount>0){
                count++;
            }
        }
        if(count <= sysTokenInfos.size()){
            return new AjaxResult(AjaxResult.Type.SUCCESS,"成功更新：" +count+ "条记录~",null);
        }else{
            return new AjaxResult(AjaxResult.Type.ERROR,"成功",null);
        }
    }

    @Override
    public SysTokenInfo selectTokenInfoOrderTopOne() {
        return sysTokenInfoMapper.selectTokenInfoOrderTopOne();
    }


}
