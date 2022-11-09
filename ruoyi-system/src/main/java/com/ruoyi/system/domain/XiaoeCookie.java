package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * cookie对象 xiaoe_cookie
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public class XiaoeCookie extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String storeName;

    /**  */
    @Excel(name = "")
    private String account;

    /**  */
    @Excel(name = "")
    private Long status;

    /**  */
    @Excel(name = "")
    private Long queueStatus;

    /**  */
    @Excel(name = "")
    private Long standbyStatus;

    /**  */
    @Excel(name = "")
    private String port;

    /**  */
    @Excel(name = "")
    private Long quantity;

    /**  */
    @Excel(name = "")
    private Long succNum;

    /**  */
    @Excel(name = "")
    private Long failQuantity;

    /**  */
    @Excel(name = "")
    private String remak;

    /**  */
    @Excel(name = "")
    private String cookie;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date created;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoreName(String storeName) 
    {
        this.storeName = storeName;
    }

    public String getStoreName() 
    {
        return storeName;
    }
    public void setAccount(String account) 
    {
        this.account = account;
    }

    public String getAccount() 
    {
        return account;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setQueueStatus(Long queueStatus) 
    {
        this.queueStatus = queueStatus;
    }

    public Long getQueueStatus() 
    {
        return queueStatus;
    }
    public void setStandbyStatus(Long standbyStatus) 
    {
        this.standbyStatus = standbyStatus;
    }

    public Long getStandbyStatus() 
    {
        return standbyStatus;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setQuantity(Long quantity) 
    {
        this.quantity = quantity;
    }

    public Long getQuantity() 
    {
        return quantity;
    }
    public void setSuccNum(Long succNum) 
    {
        this.succNum = succNum;
    }

    public Long getSuccNum() 
    {
        return succNum;
    }
    public void setFailQuantity(Long failQuantity) 
    {
        this.failQuantity = failQuantity;
    }

    public Long getFailQuantity() 
    {
        return failQuantity;
    }
    public void setRemak(String remak) 
    {
        this.remak = remak;
    }

    public String getRemak() 
    {
        return remak;
    }
    public void setCookie(String cookie) 
    {
        this.cookie = cookie;
    }

    public String getCookie() 
    {
        return cookie;
    }
    public void setCreated(Date created) 
    {
        this.created = created;
    }

    public Date getCreated() 
    {
        return created;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storeName", getStoreName())
            .append("account", getAccount())
            .append("status", getStatus())
            .append("queueStatus", getQueueStatus())
            .append("standbyStatus", getStandbyStatus())
            .append("port", getPort())
            .append("quantity", getQuantity())
            .append("succNum", getSuccNum())
            .append("failQuantity", getFailQuantity())
            .append("remak", getRemak())
            .append("cookie", getCookie())
            .append("created", getCreated())
            .toString();
    }
}
