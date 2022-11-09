package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.XiaoeCookie;

/**
 * cookieService接口
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public interface IXiaoeCookieService 
{
    /**
     * 查询cookie
     * 
     * @param id cookie主键
     * @return cookie
     */
    public XiaoeCookie selectXiaoeCookieById(Long id);

    /**
     * 查询cookie列表
     * 
     * @param xiaoeCookie cookie
     * @return cookie集合
     */
    public List<XiaoeCookie> selectXiaoeCookieList(XiaoeCookie xiaoeCookie);

    /**
     * 新增cookie
     * 
     * @param xiaoeCookie cookie
     * @return 结果
     */
    public int insertXiaoeCookie(XiaoeCookie xiaoeCookie);

    /**
     * 修改cookie
     * 
     * @param xiaoeCookie cookie
     * @return 结果
     */
    public int updateXiaoeCookie(XiaoeCookie xiaoeCookie);

    /**
     * 批量删除cookie
     * 
     * @param ids 需要删除的cookie主键集合
     * @return 结果
     */
    public int deleteXiaoeCookieByIds(String ids);

    /**
     * 删除cookie信息
     * 
     * @param id cookie主键
     * @return 结果
     */
    public int deleteXiaoeCookieById(Long id);
}
