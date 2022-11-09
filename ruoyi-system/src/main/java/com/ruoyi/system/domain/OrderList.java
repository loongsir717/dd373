package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * orders对象 order_list
 * 
 * @author ruoyi
 * @date 2022-08-27
 */
public class OrderList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 手机 */
    @Excel(name = "手机")
    private String phone;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal price;

    /** 下单地址 */
    @Excel(name = "下单地址")
    private String addDown;

    /** 支付地址 */
    @Excel(name = "支付地址")
    private String payAdd;

    /** 支付方式 */
    @Excel(name = "支付方式")
    private String payType;

    /** cookie */
    @Excel(name = "cookie")
    private String cookie;

    /** body */
    @Excel(name = "body")
    private String body;

    /** response */
    @Excel(name = "response")
    private String response;

    /** 支付状态 */
    @Excel(name = "支付状态")
    private Long payStatus;

    /** 时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatad;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date compTime;

    /** 预产 */
    @Excel(name = "预产")
    private String yuchang;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setPrice(BigDecimal price) 
    {
        this.price = price;
    }

    public BigDecimal getPrice() 
    {
        return price;
    }
    public void setAddDown(String addDown) 
    {
        this.addDown = addDown;
    }

    public String getAddDown() 
    {
        return addDown;
    }
    public void setPayAdd(String payAdd) 
    {
        this.payAdd = payAdd;
    }

    public String getPayAdd() 
    {
        return payAdd;
    }
    public void setPayType(String payType) 
    {
        this.payType = payType;
    }

    public String getPayType() 
    {
        return payType;
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
    public void setPayStatus(Long payStatus) 
    {
        this.payStatus = payStatus;
    }

    public Long getPayStatus() 
    {
        return payStatus;
    }
    public void setCreatad(Date creatad) 
    {
        this.creatad = creatad;
    }

    public Date getCreatad() 
    {
        return creatad;
    }
    public void setCompTime(Date compTime)
    {
        this.compTime = compTime;
    }

    public Date getCompTime()
    {
        return compTime;
    }
    public void setYuchang(String yuchang) 
    {
        this.yuchang = yuchang;
    }

    public String getYuchang() 
    {
        return yuchang;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("phone", getPhone())
            .append("price", getPrice())
            .append("addDown", getAddDown())
            .append("payAdd", getPayAdd())
            .append("payType", getPayType())
            .append("cookie", getCookie())
            .append("body", getBody())
            .append("response", getResponse())
            .append("payStatus", getPayStatus())
            .append("creatad", getCreatad())
            .append("compTime:", getCompTime())
            .append("yuchang", getYuchang())
            .toString();
    }
}
