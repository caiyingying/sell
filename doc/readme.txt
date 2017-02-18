1、登录页面的优化 -- 完成
2、扫码绑定添加，重新绑定后，原本的结束时间，设置为当前的开始时间。添加用户手机号
3、买家手机号，查询，不能重复
4、单价，数量，原金额，折扣单价，折扣金额，返现金额，返现时间。统计：原金额，折扣金额，返现金额


1、品牌故事 http://hzydsw.com/sell/introduce
2、最新资讯 https://mp.weixin.qq.com/mp/profile_ext?action=home&__biz=MzI5MjQwNzQyNw==&scene=124#wechat_redirect
3、进入店铺 https://weidian.com/?userid=1151491752&wfr=c


{
     "button":[
     {
          "type":"view",
          "name":"品牌故事",
          "url":"http://hzydsw.com/sell/introduce"
      },
	  {
          "type":"view",
          "name":"最新资讯",
          "url":"http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzI5MjQwNzQyNw==&from=1#wechat_webview_type=1&wechat_redirect"
      },
	  {
          "type":"view",
          "name":"进入店铺",
          "url":"https://weidian.com/?userid=1151491752&wfr=c"
      }
	  ]
}

1、订单统计中的全选
2、金额汇总保留2位小数
3、返现时候选中多条记录，只返现了第一条 --完成
4、返现时间的格式化 --完成
5、返现的时候保存当时的返现折扣等 --完成

最新资讯
http://mp.weixin.qq.com/mp/getmasssendmsg?__biz=MzIwNDc0NzAwOA==&from=1#wechat_webview_type=1&wechat_redirect
联系我们
http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000006&idx=1&sn=8f09a2eb96fe2d94f025eab07a2e0b86&chksm=173a3969204db07fb69de2bce8e030c784f8796fb7fd7fa07b49abe2daf4ed99e6063c210400&scene=18#wechat_redirect



var reg = /^1[3|4|5|7|8][0-9]{9}$/; //验证规则
var phoneNum = '15507621999';//手机号码
var flag = reg.test(phoneNum); //true
var reg = /^1[0-9]{10}$/;

1、扫码时候创建分销商和openId的关系，不设置生效时间和失效时间
2、绑定手机号码的时候，设置生效时间为当前，同时设置存在生效时间的关系中的失效时间为当前。(存在失效时间即为失效，不存在即为生效)
3、同步订单的时候，去客户分销商关系中查询分销商和客户的关系，保存分销商。
4、订单查询，只能查询我关联的分销商
5、同步时页面的锁定
