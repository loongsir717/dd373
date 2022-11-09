package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.XiaoeCookieMapper;
import com.ruoyi.system.domain.XiaoeCookie;
import com.ruoyi.system.service.IXiaoeCookieService;
import com.ruoyi.common.core.text.Convert;

/**
 * cookieService业务层处理
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
@Service
public class XiaoeCookieServiceImpl implements IXiaoeCookieService 
{
    @Autowired
    private XiaoeCookieMapper xiaoeCookieMapper;

    /**
     * 查询cookie
     * 
     * @param id cookie主键
     * @return cookie
     */
    @Override
    public XiaoeCookie selectXiaoeCookieById(Long id)
    {
        return xiaoeCookieMapper.selectXiaoeCookieById(id);
    }

    /**
     * 查询cookie列表
     * 
     * @param xiaoeCookie cookie
     * @return cookie
     */
    @Override
    public List<XiaoeCookie> selectXiaoeCookieList(XiaoeCookie xiaoeCookie)
    {
        return xiaoeCookieMapper.selectXiaoeCookieList(xiaoeCookie);
    }

    /**
     * 新增cookie
     * 
     * @param xiaoeCookie cookie
     * @return 结果
     */
    @Override
    public int insertXiaoeCookie(XiaoeCookie xiaoeCookie)
    {
        return xiaoeCookieMapper.insertXiaoeCookie(xiaoeCookie);
    }

    /**
     * 修改cookie
     * 
     * @param xiaoeCookie cookie
     * @return 结果
     */
    @Override
    public int updateXiaoeCookie(XiaoeCookie xiaoeCookie)
    {
        return xiaoeCookieMapper.updateXiaoeCookie(xiaoeCookie);
    }

    /**
     * 批量删除cookie
     * 
     * @param ids 需要删除的cookie主键
     * @return 结果
     */
    @Override
    public int deleteXiaoeCookieByIds(String ids)
    {
        return xiaoeCookieMapper.deleteXiaoeCookieByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除cookie信息
     * 
     * @param id cookie主键
     * @return 结果
     */
    @Override
    public int deleteXiaoeCookieById(Long id)
    {
        return xiaoeCookieMapper.deleteXiaoeCookieById(id);
    }
}
