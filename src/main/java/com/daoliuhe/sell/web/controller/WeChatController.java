package com.daoliuhe.sell.web.controller;

import com.daoliuhe.sell.bean.WeChat;
import com.daoliuhe.sell.service.WeChatService;
import com.daoliuhe.sell.util.PropertyHandler;
import com.daoliuhe.sell.util.Utils;
import com.daoliuhe.sell.weChat.TokenHandler;
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
import java.util.Calendar;

/**
 * Created by CYY on 2016/12/25.
 */
@Controller
@RequestMapping("weChat")
public class WeChatController {

    private static final Logger logger = LoggerFactory.getLogger(WeChatController.class);

    /**
     * access_token
     */
    @Autowired
    private TokenHandler tokenHandler;

    @Resource
    WeChatService weChatService;

    /**
     * 校验信息是否是从微信服务器发过来的。
     *
     * @param weChat
     * @param out
     */
    @RequestMapping(method = {RequestMethod.GET}, produces = "application/json;charset=UTF-8")
    public void valid(WeChat weChat, PrintWriter out) {
        String signature = weChat.getSignature(); // 微信加密签名
        String timestamp = weChat.getTimestamp(); // 时间戳
        String nonce = weChat.getNonce();// 随机数
        String echostr = weChat.getEchostr();// 随机字符串

        String token = PropertyHandler.getToken();
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (Utils.checkSignature(token, signature, timestamp, nonce)) {
            out.print(echostr);
        } else {
            logger.warn("不是微信服务器发来的请求,请小心!");
        }
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
        ServletInputStream in = request.getInputStream();
        String xmlMsg = Utils.inputStream2String(in);
        logger.info("输入消息:[" + xmlMsg + "]");

        String accessToken = tokenHandler.getToke();
        logger.info("accessToken:{}", accessToken);

        String respMessage = "";
        StringBuffer message = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        long endTime = calendar.getTimeInMillis();
        message.append("<xml>")
                .append("<ToUserName><![CDATA[o3swquEwkgq-SPvMUB-ctFUYUsR8]]></ToUserName>")
                //.append("<ToUserName><![CDATA[o3swquAupwYguV-d8qPf_yW7BQ_g]]></ToUserName>")
                .append("<FromUserName><![CDATA[gh_b7da08268238]]></FromUserName>")
                .append("<CreateTime>" + endTime + "</CreateTime>")
                .append("<MsgType><![CDATA[text]]></MsgType>")
                .append("<Content><![CDATA[你好]]></Content>")
                .append("</xml>");
        respMessage = message.toString();
        logger.info(respMessage);
        // 响应消息
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }
}
