package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺对象 dianpu
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public class Dianpu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /**  */
    @Excel(name = "")
    private String appid;

    /**  */
    @Excel(name = "")
    private String clientid;

    /**  */
    @Excel(name = "")
    private String clientsec;

    /**  */
    @Excel(name = "")
    private String msgToken;

    /**  */
    @Excel(name = "")
    private String key;

    /** 推送地址 */
    @Excel(name = "推送地址")
    private String adderss;

    /** 收款方式 */
    @Excel(name = "收款方式")
    private Long type;

    /** cookie有效时长 */
    @Excel(name = "cookie有效时长")
    private Long etime;

    /** 生成cookie数量 */
    @Excel(name = "生成cookie数量")
    private Long cookisNum;

    /** 下单成本限制 */
    @Excel(name = "下单成本限制")
    private Long succCooNum;

    /** 状态 */
    @Excel(name = "状态")
    private Long status;

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
    public void setMsgToken(String msgToken) 
    {
        this.msgToken = msgToken;
    }

    public String getMsgToken() 
    {
        return msgToken;
    }
    public void setKey(String key) 
    {
        this.key = key;
    }

    public String getKey() 
    {
        return key;
    }
    public void setAdderss(String adderss) 
    {
        this.adderss = adderss;
    }

    public String getAdderss() 
    {
        return adderss;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }
    public void setEtime(Long etime)
    {
        this.etime = etime;
    }
    public Long getEtime()
    {
        return etime;
    }
    public void setCookisNum(Long cookisNum) 
    {
        this.cookisNum = cookisNum;
    }

    public Long getCookisNum() 
    {
        return cookisNum;
    }
    public void setSuccCooNum(Long succCooNum) 
    {
        this.succCooNum = succCooNum;
    }

    public Long getSuccCooNum() 
    {
        return succCooNum;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("appid", getAppid())
            .append("clientid", getClientid())
            .append("clientsec", getClientsec())
            .append("msgToken", getMsgToken())
            .append("key", getKey())
            .append("adderss", getAdderss())
            .append("type", getType())
            .append("etime", getEtime())
            .append("cookisNum", getCookisNum())
            .append("succCooNum", getSuccCooNum())
            .append("status", getStatus())
            .toString();
    }
}
