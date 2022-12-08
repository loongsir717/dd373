package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多多373店铺对象 ddpayshop
 *
 * @author ruoyi
 * @date 2022-11-09
 */
public class Ddpayshop extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String name;

    /** appid */
    @Excel(name = "appid")
    private String appid;

    /** clientid */
    @Excel(name = "clientid")
    private String clientid;

    /** clientsec */
    @Excel(name = "clientsec")
    private String clientsec;



    /** cookie */
    @Excel(name = "cookie")
    private String cookie;

//    /** key */
//    @Excel(name = "key")
//    private String key;
//
//    /** 推送地址 */
//    @Excel(name = "推送地址")
//    private String pushUrl;

    /** 收款方式 */
    @Excel(name = "收款方式")
    private String payMethod;

    /** cookie有效时长（秒） */
    @Excel(name = "cookie有效时长", readConverterExp = "秒=")
    private Long cookieExpire;

    /** cookie启动个数（0任务停止） */
    @Excel(name = "cookie启动个数", readConverterExp = "0=任务停止")
    private Long cookieCount;

    /** cookie最大订单数（0不限制） */
    @Excel(name = "cookie最大订单数", readConverterExp = "0=不限制")
    private Long cookieMaxOrder;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 是否删除 */
    @Excel(name = "是否删除")
    private Integer deleted;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createId;

    /** 最后修改人ID */
    @Excel(name = "最后修改人ID")
    private Long updateId;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public String getAppid()
    {
        return appid;
    }
    public void setClientid(String clientid)
    {
        this.clientid = clientid;
    }

    public String getClientid()
    {
        return clientid;
    }
    public void setClientsec(String clientsec)
    {
        this.clientsec = clientsec;
    }

    public String getClientsec()
    {
        return clientsec;
    }
//    public void setMsgToken(String msgToken)
//    {
//        this.msgToken = msgToken;
//    }
//
//    public String getMsgToken()
//    {
//        return msgToken;
//    }
    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }

    public String getCookie()
    {
        return cookie;
    }
//    public void setKey(String key)
//    {
//        this.key = key;
//    }
//
//    public String getKey()
//    {
//        return key;
//    }
//    public void setPushUrl(String pushUrl)
//    {
//        this.pushUrl = pushUrl;
//    }
//
//    public String getPushUrl()
//    {
//        return pushUrl;
//    }
    public void setPayMethod(String payMethod)
    {
        this.payMethod = payMethod;
    }

    public String getPayMethod()
    {
        return payMethod;
    }
    public void setCookieExpire(Long cookieExpire)
    {
        this.cookieExpire = cookieExpire;
    }

    public Long getCookieExpire()
    {
        return cookieExpire;
    }
    public void setCookieCount(Long cookieCount)
    {
        this.cookieCount = cookieCount;
    }

    public Long getCookieCount()
    {
        return cookieCount;
    }
    public void setCookieMaxOrder(Long cookieMaxOrder)
    {
        this.cookieMaxOrder = cookieMaxOrder;
    }

    public Long getCookieMaxOrder()
    {
        return cookieMaxOrder;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setDeleted(Integer deleted)
    {
        this.deleted = deleted;
    }

    public Integer getDeleted()
    {
        return deleted;
    }
    public void setCreateId(Long createId)
    {
        this.createId = createId;
    }

    public Long getCreateId()
    {
        return createId;
    }
    public void setUpdateId(Long updateId)
    {
        this.updateId = updateId;
    }

    public Long getUpdateId()
    {
        return updateId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("appid", getAppid())
            .append("clientid", getClientid())
            .append("clientsec", getClientsec())
//            .append("msgToken", getMsgToken())
            .append("cookie", getCookie())
//            .append("key", getKey())
//            .append("pushUrl", getPushUrl())
            .append("payMethod", getPayMethod())
            .append("cookieExpire", getCookieExpire())
            .append("cookieCount", getCookieCount())
            .append("cookieMaxOrder", getCookieMaxOrder())
            .append("status", getStatus())
            .append("deleted", getDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("createId", getCreateId())
            .append("updateId", getUpdateId())
            .toString();
    }
}
