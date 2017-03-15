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
    <title>萤火虫Deslucioles</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("div[name='item']").click(function () {
                var link_url = $(this).attr("link_url");
                //window.location.href = "http://mp.weixin.qq.com/s/8Zy_zmbpQVmVZDbvKOe5rg";
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
                    品牌文化
                </a>
            </li>
            <li class="col-xs-4 base" style="background-color: #f2f2f2">
                <a href="#body" data-toggle="tab"
                   style="text-align: center; font-size: 40px; border:0; background-color: #f2f2f2">
                    视频讲解
                </a>
            </li>
            <li class="col-xs-4 base" style="background-color: #f2f2f2">
                <a href="#living" data-toggle="tab"
                   style="text-align: center; font-size: 40px; border:0; background-color: #f2f2f2">
                    健康知识
                </a>
            </li>
        </ul>

        <div id="tabContent" class="tab-content">
            <!--品牌文化-->
            <div class="tab-pane active" id="hair">
                <div class="col-xs-12" name="item" style="margin-top: 20px;" link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000007&idx=1&sn=896cb2f9bd6754b166b40a1031ade9a9&chksm=173a3968204db07e251da8dfe0c921bb5b2253c75ecba0251314d54284e7af8b1d5b138570b6#wechat_redirect">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/22.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">品牌理念</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;">中国原创有机品牌“萤火虫”有机产品，传递健康、天然、有机、环保时尚的生活态度！</p>
                    </div>
                </div>
                <div class="splitLine"></div>
            </div>

            <!--视频讲解-->
            <div class="tab-pane" id="body">
                <div class="col-xs-12" name="item" style="margin-top: 20px;" link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000008&idx=1&sn=70c3063c586ae38e54c96aa0169bd10c&chksm=173a3967204db071e6a361694a7b481d9c715bf76fe9b839a6fec23beb00564eb6d790c69490#wechat_redirect">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/logo21.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">萤火虫天然有机系列</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
            </div>

            <!--健康知识-->
            <div class="tab-pane" id="living">
                <div class="col-xs-12" name="item" style="margin-top: 20px;" link_url="http://mp.weixin.qq.com/s?__biz=MzIwNDc0NzAwOA==&mid=100000009&idx=1&sn=bc655ea09c2fe0d2ccd603e0e3ff9360&chksm=173a3966204db070d4fd2114381e017a0d5344edbb3a59e69b803575845d5782286b262f7e93#wechat_redirect">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/logo31.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3 style="font-size: 40px;">化学洗护用品背后的危害真相</h3>
                        <p style="font-size: 34px;font-color: #f2f2f2;"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>