package com.daoliuhe.sell.util;

public class WeChatConstants {
    /**
     * 获取access token
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     */
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?";

    /**
     * 通过OpenID来获取用户基本信息
     * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     */
    public final static String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?";

    /**
     * http请求方式: POST
     * URL: https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN
     * POST数据格式：json
     * POST数据例子：{"action_name": "QR_LIMIT_SCENE", "action_info": {"scene": {"scene_id": 123}}}
     * 或者也可以使用以下POST数据创建字符串形式的二维码参数：
     * {"action_name": "QR_LIMIT_STR_SCENE", "action_info": {"scene": {"scene_str": "123"}}}
     */
    public final static String qrcode = "https://api.weixin.qq.com/cgi-bin/qrcode/create?";

    /**
     * 缓存的token名字
     */
    public final static String accessToken = "accessToken";

    /**
     * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";

    /**
     * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    public final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
}
