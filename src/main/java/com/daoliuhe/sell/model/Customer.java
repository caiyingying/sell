package com.daoliuhe.sell.model;

import com.daoliuhe.sell.util.Utils;

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
     * 生效时间
     */
    private Date enableDate;

    /**
     * 生效时间字符串
     */
    private String enableDateStr;

    /**
     * 失效时间
     */
    private Date disableDate;

    /**
     * 失效时间字符串
     */
    private String disableDateStr;

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
        if (null != enableDate) {
            this.enableDateStr = Utils.dateFormat(enableDate);
        }
    }

    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
        if (null != disableDate) {
            this.disableDateStr = Utils.dateFormat(disableDate);
        }
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

    public String getEnableDateStr() {
        return enableDateStr;
    }

    public void setEnableDateStr(String enableDateStr) {
        this.enableDateStr = enableDateStr;
    }

    public String getDisableDateStr() {
        return disableDateStr;
    }

    public void setDisableDateStr(String disableDateStr) {
        this.disableDateStr = disableDateStr;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", wechat='" + wechat + '\'' +
                ", nick='" + nick + '\'' +
                ", businessId=" + businessId +
                ", businessName='" + businessName + '\'' +
                ", phone='" + phone + '\'' +
                ", enableDate=" + enableDate +
                ", disableDate=" + disableDate +
                '}';
    }
}