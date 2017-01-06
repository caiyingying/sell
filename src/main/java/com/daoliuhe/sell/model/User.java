package com.daoliuhe.sell.model;

import java.io.Serializable;

public class User extends BasePage implements Serializable {
    private Integer id;

    private String loginName;

    private String userPassword;

    private String userName;

    private String enabled;

    private String mobile;

    private String wechat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", userPassword=' ***** " + '\'' +
                ", userName='" + userName + '\'' +
                ", enabled='" + enabled + '\'' +
                ", mobile='" + mobile + '\'' +
                ", wechat='" + wechat + '\'' +
                '}';
    }
}