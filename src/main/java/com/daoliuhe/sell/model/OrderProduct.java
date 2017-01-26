package com.daoliuhe.sell.model;

import java.util.Date;

public class OrderProduct extends BasePage{
    private Integer id;

    private String orderId;

    private Double orderTotalPrice;

    private String payTime;

    private String userPhone;

    private Integer dealersId;

    private String comfirm;

    private Date comfirmDate;

    private String resc;

    private String itemId;

    private String itemName;

    private Double price;

    private Integer quantity;

    private Double totalPrice;

    private Double rebate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Integer getDealersId() {
        return dealersId;
    }

    public void setDealersId(Integer dealersId) {
        this.dealersId = dealersId;
    }

    public String getComfirm() {
        return comfirm;
    }

    public void setComfirm(String comfirm) {
        this.comfirm = comfirm;
    }

    public Date getComfirmDate() {
        return comfirmDate;
    }

    public void setComfirmDate(Date comfirmDate) {
        this.comfirmDate = comfirmDate;
    }

    public String getResc() {
        return resc;
    }

    public void setResc(String resc) {
        this.resc = resc;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getRebate() {
        return rebate;
    }

    public void setRebate(Double rebate) {
        this.rebate = rebate;
    }
}