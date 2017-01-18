package com.daoliuhe.sell.weChat.bean;

/**
 * Created by CYY on 2017/1/18.
 */
public class OrderRet {
    private String errcode;//错误码
    private String errmsg;//错误信息
    private Order order;//订单详情

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
