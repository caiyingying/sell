package com.daoliuhe.sell.model;

import com.daoliuhe.sell.util.Utils;

import java.util.Date;
import java.util.List;

public class OrderProduct extends BasePage {
    private Integer id;

    private String orderId;

    private Double orderTotalPrice;

    private String payTime;

    private String payTimeBegin;

    private String payTimeEnd;

    private String userPhone;

    private Integer dealersId;

    private List<Integer> dealersIdList;

    private String comfirm;

    private Date comfirmDate;

    private String comfirmDateStr;

    private String resc;

    private String itemId;

    private String itemName;

    private Double price;

    private Integer quantity;

    private Double totalPrice;

    private Double discountPrice;

    private Double discountTotalPrice;

    private Double rebate;

    private String skuId;

    private String skuTitle;

    //分销商名称
    private String dealerName;

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
        if (null != comfirmDate) {
            this.comfirmDateStr = Utils.dateFormat(comfirmDate);
        }
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

    public Double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(Double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public Double getDiscountTotalPrice() {
        return discountTotalPrice;
    }

    public void setDiscountTotalPrice(Double discountTotalPrice) {
        this.discountTotalPrice = discountTotalPrice;
    }

    public String getPayTimeBegin() {
        return payTimeBegin;
    }

    public void setPayTimeBegin(String payTimeBegin) {
        this.payTimeBegin = payTimeBegin;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public String getSkuTitle() {
        return skuTitle;
    }

    public void setSkuTitle(String skuTitle) {
        this.skuTitle = skuTitle;
    }

    public String getPayTimeEnd() {

        return payTimeEnd;
    }

    public void setPayTimeEnd(String payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getComfirmDateStr() {
        return comfirmDateStr;
    }

    public void setComfirmDateStr(String comfirmDateStr) {
        this.comfirmDateStr = comfirmDateStr;
    }

    public List<Integer> getDealersIdList() {
        return dealersIdList;
    }

    public void setDealersIdList(List<Integer> dealersIdList) {
        this.dealersIdList = dealersIdList;
    }
}