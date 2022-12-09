package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * TokenInfo对象 sys_token_info
 *
 * @author ruoyi
 * @date 2022-12-08
 */
public class SysTokenInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 店铺Id */
    @Excel(name = "店铺Id")
    private String shopId;

    /** 账号 */
    @Excel(name = "账号")
    private String username;

    /** 排队状态 */
    @Excel(name = "排队状态")
    private Long queue;

    /** 备用状态 */
    @Excel(name = "备用状态")
    private String backup;

    /** cookie */
    @Excel(name = "cookie")
    private String cookie;

    /** 描述 */
    @Excel(name = "描述")
    private String remarks;


    /** 描述 */
    @Excel(name = "用户地址")
    private String userAdderss;

    public String getUserAdderss() {
        return userAdderss;
    }

    public void setUserAdderss(String userAdderss) {
        this.userAdderss = userAdderss;
    }

    public Long getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(Long countOrder) {
        this.countOrder = countOrder;
    }

    /** pwd */
    @Excel(name = "pwd")
    private String pwd;

    /** countOrder */
    @Excel(name = "countOrder")
    private Long countOrder;



    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setShopId(String shopId)
    {
        this.shopId = shopId;
    }

    public String getShopId()
    {
        return shopId;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
    public void setQueue(Long queue)
    {
        this.queue = queue;
    }

    public Long getQueue()
    {
        return queue;
    }
    public void setBackup(String backup)
    {
        this.backup = backup;
    }

    public String getBackup()
    {
        return backup;
    }
    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }

    public String getCookie()
    {
        return cookie;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }

    public String getPwd()
    {
        return pwd;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("shopId", getShopId())
            .append("username", getUsername())
            .append("queue", getQueue())
            .append("backup", getBackup())
            .append("cookie", getCookie())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("remarks", getRemarks())
            .append("pwd", getPwd())
            .append("userAdderss", getUserAdderss())
            .append("countOrder", getCountOrder())
            .toString();
    }
}
