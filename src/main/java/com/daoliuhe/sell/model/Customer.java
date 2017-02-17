package com.daoliuhe.sell.model;

import java.util.Date;

public class Customer extends BasePage {

    /**
     * id
     */
    private Integer id;

    /**
     * 微信的open_id
     */
    private String wechat;

    /**
     *微信昵称
     */
    private String nick;
    /**
     * 分销商的id
     */
    private Integer businessId;

    /**
     * 分销商名称
     */
    private String businessName;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 启用时间
     */
    private Date enableDate;

    /**
     * 禁用时间
     */
    private Date disableDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getEnableDate() {
        return enableDate;
    }

    public void setEnableDate(Date enableDate) {
        this.enableDate = enableDate;
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }


}