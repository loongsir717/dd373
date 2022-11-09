package com.ruoyi.common.core.domain.entity;

/**
 * 功能描述
 *
 * @author: scott
 * @date: 2022年11月09日 16:37
 */
public class OrderLinkJson {

    private String OrderId;
    private String BankCode;
    private String ScenceType;
    private Integer PayScene;
    private String VerifyCode;
    private String Purpose;
    private Integer SendWay;
    private Boolean IsWap = true;
    private String PayTypeId;

    public String getOrderId() {
        return OrderId;
    }

    public void setOrderId(String orderId) {
        OrderId = orderId;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getScenceType() {
        return ScenceType;
    }

    public void setScenceType(String scenceType) {
        ScenceType = scenceType;
    }

    public Integer getPayScene() {
        return PayScene;
    }

    public void setPayScene(Integer payScene) {
        PayScene = payScene;
    }

    public String getVerifyCode() {
        return VerifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        VerifyCode = verifyCode;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public Integer getSendWay() {
        return SendWay;
    }

    public void setSendWay(Integer sendWay) {
        SendWay = sendWay;
    }

    public Boolean getIsWap() {
        return IsWap;
    }

    public void setIsWap(Boolean isWap) {
        IsWap = isWap;
    }

    public String getPayTypeId() {
        return PayTypeId;
    }

    public void setPayTypeId(String payTypeId) {
        PayTypeId = payTypeId;
    }

    @Override
    public String toString() {
        return "{\"OrderId\":\""+OrderId+"\",\"BankCode\":\""+BankCode+"\",\"ScenceType\":\""+ScenceType+"\",\"PayScene\":\""+PayScene+"\",\"VerifyCode\":\""+VerifyCode+"\",\"Purpose\":\""+Purpose+"\",\"SendWay\":\""+SendWay+"\",\"IsWap\":\""+IsWap+"\",\"PayTypeId\":\""+PayTypeId+"\"}";

    }
}

