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
                window.location.href = "http://mp.weixin.qq.com/s/8Zy_zmbpQVmVZDbvKOe5rg";
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
                        <img src="static/images/index1.jpg" alt="..." width="100%" style="width: 100%;">
                        <div class="carousel-caption">
                            <h3>您选对了，再小的力量也是一种支持</h3>
                        </div>
                    </div>
                    <div class="item">
                        <img src="static/images/index2.jpg" alt="..." width="100%" style="width: 100%;">
                        <div class="carousel-caption">
                            <h3>您选对了，再小的力量也是一种支持</h3>
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
                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index1.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index1.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index1.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>
            </div>

            <!--身体-->
            <div class="tab-pane" id="body">
                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index2.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index2.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index2.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>
            </div>

            <!--居家-->
            <div class="tab-pane" id="living">
                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index3.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index3.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>

                <div class="splitLine"></div>

                <div class="col-xs-12" name="item" style="margin-top: 20px;">
                    <div class="col-xs-4 thumbnail" style="border:0;">
                        <img src="static/images/index3.jpg" alt="holder.js/300x300">
                    </div>
                    <div class="col-xs-8 caption">
                        <h3>品牌理念</h3>
                        <p>理念是一个美好的愿景，对于用户、员工、环境及社会都【好】的事业...</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>