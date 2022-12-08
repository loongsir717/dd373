package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
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
}
