package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2022年11月09日 15:17
 */
public class DdPayOrderApi extends BaseEntity {

    public String merchantNo ;
    public String merchantOrderNo  ;
    public String payMethod  ;
    public String shareurl  ;
    public String callBack  ;
    public Integer amount  ;
    public String channelNo  ;
    public String appid  ;
    public String sign  ;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantOrderNo() {
        return merchantOrderNo;
    }

    public void setMerchantOrderNo(String merchantOrderNo) {
        this.merchantOrderNo = merchantOrderNo;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public String getShareurl() {
        return shareurl;
    }

    public void setShareurl(String shareurl) {
        this.shareurl = shareurl;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(String channelNo) {
        this.channelNo = channelNo;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
