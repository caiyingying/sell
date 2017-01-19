package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.bean.WeChat;
import com.daoliuhe.sell.service.WeChatService;
import com.daoliuhe.sell.util.PropertyHandler;
import com.daoliuhe.sell.util.Utils;
import com.daoliuhe.sell.weChat.TokenHandler;
import com.daoliuhe.sell.weChat.WeiDianTokenHandler;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 微店的推送消息
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("weiDianPush")
public class WeiDianController {

    private static final Logger logger = LoggerFactory.getLogger(WeiDianController.class);

    /**
     * access_token
     */
    @Autowired
    private WeiDianTokenHandler tokenHandler;
    /**
     * @param request
     * @param response
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void valid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 响应消息
        PrintWriter out = response.getWriter();
        JSONObject retJson = new JSONObject();
        retJson.put("status", "success");
        out.print(retJson.toString());
        out.flush();
        out.close();
    }

    /**
     * 微信消息的处理
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(method = {RequestMethod.POST}, produces = "application/xml;charset=UTF-8")
    public void dispose(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        /* 消息的接收、处理、响应 */
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 调用核心业务类接收消息、处理消息
        PrintWriter out = response.getWriter();
        JSONObject retJson = new JSONObject();
        retJson.put("status", "success");
        out.print(retJson.toString());
        out.flush();
        out.close();

    }
}
