package com.daoliuhe.sell.weChat.bean;

/**
 * Created by CYY on 2017/1/15.
 * 微信创建二维码请求
 */
public class Action {
    private String action_name;

    private ActionInfo action_info;

    public String getAction_name() {
        return action_name;
    }

    public void setAction_name(String action_name) {
        this.action_name = action_name;
    }

    public ActionInfo getAction_info() {
        return action_info;
    }

    public void setAction_info(ActionInfo action_info) {
        this.action_info = action_info;
    }
}
