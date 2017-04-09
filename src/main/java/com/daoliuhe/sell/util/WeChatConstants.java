package com.daoliuhe.sell.util;

public class WeChatConstants {
    /**
     * 获取access token
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     */
    public final static String access_token_url = "https://api.weixin.qq.com/cgi-bin/token?";

    /**
     * 网页授权的access_token
     */
    public final static String web_access_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token?";

    /**
     * 获取微店的access_token
     */
    public final static String wei_dian_access_token_url = "https://api.vdian.com/token?";

    /**
     * 微店接口的baseUrl
     */
    public final static String wei_dian_base_url = "https://api.vdian.com/api?";

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

    /***
     * 查询商品
     * http请求方式 POST
     * https://api.weixin.qq.com/merchant/get?access_token=ACCESS_TOKEN
     * access_token	是	调用接口凭证
     * POST数据	是	商品信息
     * {"product_id": "pDF3iYwktviE3BzU3BKiSWWi9Nkw"}
     * product_id	商品ID
     */
    public final static String merchant_get = "https://api.weixin.qq.com/merchant/get?";

    /***
     * 获取指定状态的所有商品
     * http请求方式	POST
     * 请求Url	https://api.weixin.qq.com/merchant/getbystatus?access_token=ACCESS_TOKEN
     * access_token	是	调用接口凭证
     * POST数据	是	商品详情信息
     * {"status": 0 }
     *  status	商品状态(0-全部, 1-上架, 2-下架)
     */
    public final static String merchant_getbystatus = "https://api.weixin.qq.com/merchant/getbystatus?";

    /***
     * 根据订单状态/创建时间获取订单详情
     *
     * http请求方式	GET
     * 请求Url	https://api.weixin.qq.com/merchant/order/getbyfilter?access_token=ACCESS_TOKEN
     * POST数据格式	json
     * { "status": 2, "begintime": 1397130460, "endtime": 1397130470 }
     * status	 订单状态(不带该字段-全部状态, 2-待发货, 3-已发货, 5-已完成, 8-维权中, )
     * begintime 订单创建时间起始时间(不带该字段则不按照时间做筛选)
     * endtime	 订单创建时间终止时间(不带该字段则不按照时间做筛选)
     */
    public final static String merchant_order_getbyfilter = "https://api.weixin.qq.com/merchant/order/getbyfilter?";

    /***
     * 6.2	根据订单ID获取订单详情
     * http请求方式	POST
     * 请求Url	https://api.weixin.qq.com/merchant/order/getbyid?access_token=ACCESS_TOKEN
     * {
     * "order_id": "7197417460812584720"   订单ID
     * }
     */
    public final static String merchant_order_getbyid = "https://api.weixin.qq.com/merchant/order/getbyid?";

    /**
     * 缓存的token名字
     */
    public final static String accessToken = "accessToken";

    /**
     * 缓存的微店的token名字
     */
    public final static String weiDianAccessToken = "weiDianAccessToken";

    /**
     * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    public final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";

    /**
     * 二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久,QR_LIMIT_STR_SCENE为永久的字符串参数值
     */
    public final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";
}
