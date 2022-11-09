package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 多多373订单对象 ddpayorder
 *
 * @author ruoyi
 * @date 2022-11-09
 */
public class Ddpayorder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 店铺编号 */
    @Excel(name = "店铺编号")
    private String appid;

    /** 店铺名称 */
    @Excel(name = "店铺名称")
    private String name;

    /** 单号 */
    @Excel(name = "单号")
    private String orderId;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 下单地址 */
    @Excel(name = "下单地址")
    private String orderUrl;

    /** 支付地址 */
    @Excel(name = "支付地址")
    private String payUrl;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String method;

    /** cookie */
    @Excel(name = "cookie")
    private String cookie;

    /** body */
    @Excel(name = "body")
    private String body;

    /** 返回内容 */
    @Excel(name = "返回内容")
    private String response;

    /** 结果 */
    @Excel(name = "结果")
    private String result;

    /** 失效时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "失效时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invalidTime;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Long status;

    /** 回调地址 */
    @Excel(name = "回调地址")
    private String callbakUrl;

    /** 回调状态 */
    @Excel(name = "回调状态")
    private Long callbakStatus;

    /** 回调订单号 */
    @Excel(name = "回调订单号")
    private String merchantOrderNo;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date completionTime;

    /** 是否删除,0否,1是 */
    @Excel(name = "是否删除,0否,1是")
    private Integer deleted;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long createId;

    /** 最后修改人ID */
    @Excel(name = "最后修改人ID")
    private Long updateId;

    private  String sign;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAppid(String appid)
    {
        this.appid = appid;
    }

    public String getAppid()
    {
        return appid;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderId()
    {
        return orderId;
    }
    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getPhone()
    {
        return phone;
    }
    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }
    public void setOrderUrl(String orderUrl)
    {
        this.orderUrl = orderUrl;
    }

    public String getOrderUrl()
    {
        return orderUrl;
    }
    public void setPayUrl(String payUrl)
    {
        this.payUrl = payUrl;
    }

    public String getPayUrl()
    {
        return payUrl;
    }
    public void setMethod(String method)
    {
        this.method = method;
    }

    public String getMethod()
    {
        return method;
    }
    public void setCookie(String cookie)
    {
        this.cookie = cookie;
    }

    public String getCookie()
    {
        return cookie;
    }
    public void setBody(String body)
    {
        this.body = body;
    }

    public String getBody()
    {
        return body;
    }
    public void setResponse(String response)
    {
        this.response = response;
    }

    public String getResponse()
    {
        return response;
    }
    public void setResult(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }
    public void setInvalidTime(Date invalidTime)
    {
        this.invalidTime = invalidTime;
    }

    public Date getInvalidTime()
    {
        return invalidTime;
    }
    public void setStatus(Long status)
    {
        this.status = status;
    }

    public Long getStatus()
    {
        return status;
    }
    public void setCallbakUrl(String callbakUrl)
    {
        this.callbakUrl = callbakUrl;
    }

    public String getCallbakUrl()
    {
        return callbakUrl;
    }
    public void setCallbakStatus(Long callbakStatus)
    {
        this.callbakStatus = callbakStatus;
    }

    public Long getCallbakStatus()
    {
        return callbakStatus;
    }
    public void setMerchantOrderNo(String merchantOrderNo)
    {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getMerchantOrderNo()
    {
        return merchantOrderNo;
    }
    public void setCompletionTime(Date completionTime)
    {
        this.completionTime = completionTime;
    }

    public Date getCompletionTime()
    {
        return completionTime;
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
            .append("appid", getAppid())
            .append("name", getName())
            .append("orderId", getOrderId())
            .append("phone", getPhone())
            .append("amount", getAmount())
            .append("orderUrl", getOrderUrl())
            .append("payUrl", getPayUrl())
            .append("method", getMethod())
            .append("cookie", getCookie())
            .append("body", getBody())
            .append("response", getResponse())
            .append("result", getResult())
            .append("invalidTime", getInvalidTime())
            .append("status", getStatus())
            .append("callbakUrl", getCallbakUrl())
            .append("callbakStatus", getCallbakStatus())
            .append("merchantOrderNo", getMerchantOrderNo())
            .append("completionTime", getCompletionTime())
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
