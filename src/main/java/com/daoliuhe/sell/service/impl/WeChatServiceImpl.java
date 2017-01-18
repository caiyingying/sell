package com.daoliuhe.sell.service.impl;

import com.daoliuhe.sell.mapper.CustomerMapper;
import com.daoliuhe.sell.model.Customer;
import com.daoliuhe.sell.service.WeChatService;
import com.daoliuhe.sell.util.Utils;
import com.daoliuhe.sell.weChat.HttpKit;
import com.daoliuhe.sell.weChat.TokenHandler;
import com.daoliuhe.sell.weChat.bean.Action;
import com.daoliuhe.sell.weChat.bean.ActionReturn;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by CYY on 2017/1/15.
 */
@Service
public class WeChatServiceImpl implements WeChatService {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(WeChatServiceImpl.class);
    /**
     * access_token
     */
    @Autowired
    private TokenHandler tokenHandler;

    @Resource
    CustomerMapper customerMapper;

    @Override
    public ActionReturn createQRLimitScene() {
        Action action = new Action();
        //TODO
        return HttpKit.getQRCode(tokenHandler.getToke(), action);
    }

    @Override
    public void dispose(String xmlMsg) {
        logger.info("dispose(String xmlMsg :{})", xmlMsg);
        //解析获取的xml文件为map
        Map<String, String> msMap = Utils.parseCommand(xmlMsg);
        if (msMap.containsKey("MsgType")) {
            String msgType = msMap.get("MsgType");
            //事件消息
            if ("event".equalsIgnoreCase(msgType)) {
                String event = msMap.get("Event");
                String code = msMap.get("EventKey");
                if ("subscribe".equalsIgnoreCase(event)) {//用户未关注时，进行关注后的事件推送
                    /**
                     * ToUserName	开发者微信号
                     FromUserName	发送方帐号（一个OpenID）
                     CreateTime	消息创建时间 （整型）
                     MsgType	消息类型，event
                     Event	事件类型，subscribe
                     EventKey	事件KEY值，qrscene_为前缀，后面为二维码的参数值
                     Ticket	二维码的ticket，可用来换取二维码图片
                     */
                    code = code.replace("qrscene_","").trim();

                } else if ("SCAN".equalsIgnoreCase(event)) {//用户已关注时的事件推送
                    /**
                     * ToUserName	开发者微信号
                     FromUserName	发送方帐号（一个OpenID）
                     CreateTime	消息创建时间 （整型）
                     MsgType	消息类型，event
                     Event	事件类型，SCAN
                     EventKey	事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
                     Ticket	二维码的ticket，可用来换取二维码图片
                     */
                    code = code.replace("scene_","").trim();
                }
                String fromUserName = msMap.get("FromUserName");
                String accessToken = tokenHandler.getToke();
                String userInfo = HttpKit.getUserInfo(accessToken, fromUserName, "zh_CN");
                logger.info("userInfo: {}", userInfo);
                JSONObject userInfoJSON = JSONObject.fromObject(userInfo);
                String nickname = userInfoJSON.getString("nickname");
                //保存关系
                Customer customer = new Customer();
                Integer businessId = Integer.parseInt(code);
                customer.setBusinessId(businessId);
                customer.setNick(nickname);
                customer.setWechat(fromUserName);
                customerMapper.insertSelective(customer);
            } else {
                logger.info("不是event");
            }
        }
    }
}
