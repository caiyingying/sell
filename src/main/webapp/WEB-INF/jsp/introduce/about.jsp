<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>优达生物科技</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("div[name='item']").click(function () {
                //window.location.href = "http://mp.weixin.qq.com/s/8Zy_zmbpQVmVZDbvKOe5rg";
                var link_url = $(this).attr("link_url");
                window.location.href = link_url;
            });
            /*
             $("div[name='item']").each(function(i){
             var it = i[0];
             it.ontouchstart = function() {
             // 背景变色
             this.style.backgroundColor = "#f2f2f2";
             };

             it.ontouchend = function() {
             // 背景变色
             this.style.backgroundColor = "#ffffff";
             };
             });


             $("div[name='item']").ontouchstart(function () {
             $(this).addClass('divSelect');
             });
             $("div[name='item']").ontouchend(function () {
             $(this).removeClass('divSelect');
             });
             */
        });
    </script>
    <style type="text/css">
        a {
            color: #000000;
        }

        .nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover {
            color: #0eb83a;
        }

        div.splitLine {
            height: 1px;
            width: 100%;
            background: #f2f2f2;
            overflow: hidden;
        }

        .divSelect {
            background: #f2f2f2;
        }
    </style>
</head>
<body>
<div class="container-fluid" style="padding: 0px;">
    <div class="col-md-12" style="padding: 0px;">
        <div class="span12">
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>
                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="static/images/index0.jpeg" alt="..." width="100%" style="width: 100%;">
                        <div class="carousel-caption">
                            <h3>用头发、肌肤来感受植物的益处</h3>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/images/index2.jpg" alt="..." width="100%" style="width: 100%;">
                        <div class="carousel-caption">
                            <h3>来自国际权威SGS的检测报告，您的安心之选！</h3>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/images/index3.jpg" alt="..." width="100%" style="width: 100%;">
                        <div class="carousel-caption">
                            <h3>化学洗护用品背后的真相</h3>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="col-md-12" style="padding: 0px;">
        <ul id="myTab" class="nav nav-tabs" style="border: 0; background-color: #f2f2f2">
            <li class="col-xs-4 active base" style="background-color: #f2f2f2">
                <a href="#hair" data-toggle="tab"
                   style="text-align: center; font-size: 40px; border:0; background-color: #f2f2f2">
                    头发
                </a>
            </li>
            <li class="col-xs-4 base" style="background-color: #f2f2f2">
                <a href="#body" data-toggle="tab"
                   style="text-align: center; font-size: 40px; border:0; background-color: #f2f2f2">
                    身体
                </a>
            </li>
            <li class="col-xs-4 base" style="background-color: #f2f2f2">
                <a href="#living" data-toggle="tab"
                   style="text-align: center; font-size: 40px; border:0; background-color: #f2f2f2">
                    居家
                </a>
            </li>
        </ul>

        <div id="tabContent" class="tab-content">
            <!--头部-->
            <div class="tab-pane active" id="hair">
                <div class="col-xs-12" name="item" style="margin-top: 20px;" link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000029&idx=1&sn=7df8c949d51b9397f6720463dbb51cd2&chksm=173a3972204db0644bc0ffe4a24ac1969b1e6b21cc09801e1be8e6a0253589cb5c479a93fdd4#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_1.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">香槟玫瑰洗发精</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000032&idx=1&sn=2214f0024781b3d469f33a1e4e2720e0&chksm=173a394f204db059e6f8b877d533d75e8dbc15ec0bbd98176613d78099a93515fbbc79e3e3c0#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_2.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">香槟玫瑰护发素</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000026&idx=1&sn=0a4ad5bbe1f2ae155510d12a9666aba8&chksm=173a3975204db063e5f194d3ee292b7ba0cb0829931c60492429a18936c4a64fe95f0fa04819#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_3.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">迷迭香洗发精</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000028&idx=1&sn=b8a011c9959d50265a88007ffd051e08&chksm=173a3973204db06573c37395e71a3df5b79ea4e726ceff73847b9e2afbaa231b80ba4fe841d6#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_4.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">山茶花洗发精</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000027&idx=1&sn=1f60f7d7b84cc0f8bf05a4e0deda5aac&chksm=173a3974204db062577e40ec3c64d866e2fd478b1f21b305a658800c8dcf69f6616600cb6bb3#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_5.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">马鞭草洗发精</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
				
				
                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000031&idx=1&sn=a88493a9d33ff41a28169097893e8bec&chksm=173a3970204db0662c330ed48a84c70a088739fcbe6947f9aaa30a37b15697213c10c4d9512a#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_6.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">宝贝得洗发沐浴露</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
				
				
                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000030&idx=1&sn=8b2d8eb207c396fec2a6d3141fd1db23&chksm=173a3971204db0672a960b0161d832a4add03d3282d6ac3ed9d789f2e15388e68d1864c0b5c9#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_7.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">银发熟龄洗发精</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
				
				<div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000047&idx=1&sn=105af2c4bef558fe15f36fa6b9f83e6f&chksm=173a3940204db056f80ff3d4b42554225308bc32a74814190f3db436667fd85fa7b544cf9e9f#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_8.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">香槟玫瑰护发精油</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
				
				<div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000048&idx=1&sn=7d6758686d944f17f109ac195dc03688&chksm=173a395f204db0496580dc70ddb6b6accfc9a4c91a566a9bb803491e44ca87123af8100707bd#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_9.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">女用养发液</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
				
				<div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000049&idx=1&sn=8efec6b5c2a01762f8a7c5e31db7f60f&chksm=173a395e204db04883921a59ff49430c22dca8e5dec743c7512c2b50aeef884689c9b80a248c#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P1_10.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">男用养发液</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
            </div>

            <!--身体-->
            <div class="tab-pane" id="body">
                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000034&idx=1&sn=5b8208ba217008c01513a0307b6d7de4&chksm=173a394d204db05bb288d80059497a0f4da9835788137ad623d3d59007b3c3c0d9e2f11d3b0b#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P2_1.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">男士麝香沐浴乳</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;"  link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000033&idx=1&sn=ee6005419635af5ca058d9befe097edc&chksm=173a394e204db0589fffdbdde7b8361d58ce112aafeb395b2ee9a512ca416ce5d3a496205538#rd">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/P2_2.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">女士香水沐浴乳</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
            </div>

            <!--居家-->
            <div class="tab-pane" id="living">


            </div>

        </div>
    </div>
</div>
</body>
</html>