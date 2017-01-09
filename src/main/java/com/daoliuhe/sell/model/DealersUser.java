package com.daoliuhe.sell.model;

public class DealersUser extends BasePage{
    private Integer id;

    private Integer dealersId;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDealersId() {
        return dealersId;
    }

    public void setDealersId(Integer dealersId) {
        this.dealersId = dealersId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}