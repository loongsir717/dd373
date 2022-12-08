package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysTokenInfo;

/**
 * TokenInfoMapper接口
 * 
 * @author ruoyi
 * @date 2022-12-08
 */
public interface SysTokenInfoMapper 
{
    /**
     * 查询TokenInfo
     * 
     * @param id TokenInfo主键
     * @return TokenInfo
     */
    public SysTokenInfo selectSysTokenInfoById(Long id);

    /**
     * 查询TokenInfo列表
     * 
     * @param sysTokenInfo TokenInfo
     * @return TokenInfo集合
     */
    public List<SysTokenInfo> selectSysTokenInfoList(SysTokenInfo sysTokenInfo);

    /**
     * 新增TokenInfo
     * 
     * @param sysTokenInfo TokenInfo
     * @return 结果
     */
    public int insertSysTokenInfo(SysTokenInfo sysTokenInfo);

    /**
     * 修改TokenInfo
     * 
     * @param sysTokenInfo TokenInfo
     * @return 结果
     */
    public int updateSysTokenInfo(SysTokenInfo sysTokenInfo);

    /**
     * 删除TokenInfo
     * 
     * @param id TokenInfo主键
     * @return 结果
     */
    public int deleteSysTokenInfoById(Long id);

    /**
     * 批量删除TokenInfo
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysTokenInfoByIds(String[] ids);
}
