package com.ruoyi.system.controller.vo;

public class OrderVo {
    /**
     * 订单总数量
     */
    private Long totOrderQty;
    /**
     *订单总金额
     */
    private Long totOrderAmount;
    /**
     *支付订单总金额
     */
    private Long payOrderAmount;
    /**
     *支付订单总数量
     */
    private Long payOrderQty;
    /**
     *回调数量
     */
    private Long callbackOrderQty;

    public Long getTotOrderQty() {
        return totOrderQty;
    }

    public void setTotOrderQty(Long totOrderQty) {
        this.totOrderQty = totOrderQty;
    }

    public Long getTotOrderAmount() {
        return totOrderAmount;
    }

    public void setTotOrderAmount(Long totOrderAmount) {
        this.totOrderAmount = totOrderAmount;
    }

    public Long getPayOrderAmount() {
        return payOrderAmount;
    }

    public void setPayOrderAmount(Long payOrderAmount) {
        this.payOrderAmount = payOrderAmount;
    }

    public Long getPayOrderQty() {
        return payOrderQty;
    }

    public void setPayOrderQty(Long payOrderQty) {
        this.payOrderQty = payOrderQty;
    }

    public Long getCallbackOrderQty() {
        return callbackOrderQty;
    }

    public void setCallbackOrderQty(Long callbackOrderQty) {
        this.callbackOrderQty = callbackOrderQty;
    }
}

