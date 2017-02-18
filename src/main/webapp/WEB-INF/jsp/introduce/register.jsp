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
    <title>绑定手机号</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <style type="text/css">
        div {
            font-size: 40px;
        }
        input {
            font-size: 40px;
        }
        table {
            border-collapse: separate;
            border-spacing: 15px;
        }
    </style>
</head>
<body>
<script type="text/javascript">
    function doSubmit() {
        var reg = /^1[0-9]{10}$/;
        var phoneNum = $("#userPhone").val();//手机号码
        if(phoneNum == null && phoneNum == ""){
            var hintSpan = $("#hintSpan");//提示信息
            hintSpan.html("请输入手机号码");
        } else {
            var flag = reg.test(phoneNum); //true
            if(flag){
                $("#rForm").submit();
            } else {
                var hintSpan = $("#hintSpan");//提示信息
                hintSpan.html("请输入有效的手机号码");
            }
        }
    }

</script>
<div style="text-align: center;margin-top: 50px;">
    <form action="introduce/doRegister" method="post" id="rForm">
        <input type="hidden" name="wechat" value="${entity.wechat}"/>
        <table style="text-align: center; width: 100%;">
            <tr>
                <td colspan="2" style="color: red">
                    <span id="hintSpan"></span>
                </td>
            </tr>
            <tr>
                <td style="text-align: right;">昵称:</td>
                <td style="text-align: left;">${entity.nick}</td>
            </tr>
            <tr>
                <td style="text-align: right;">手机号码:</td>
                <td style="text-align: left;"><input type="text" name="phone" id="userPhone"/></td>
            </tr>
            <tr>
                <td colspan="2" style="color: green">
                手机号码需和微店注册的手机号码一致，便于后续推送消息和寄送礼品
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="button" value="绑定" style="width: 500px; height: 80px; " onclick="doSubmit();"/>
                </td>
            </tr>
        </table>
        <!--
        <div style="text-align: left;;">
            <label>昵称: ${entity.nick}</label>
        </div>
        <div>
            <label>手机号:<input type="text" name="phone"/></label>
        </div>
        <div>
            <input type="submit" value="绑定"/>
        </div>
        -->
        <!-- 绑定的手机号码需和微店注册的手机号码一致，方便方便后续推送消息和寄送礼品-->
    </form>
</div>
</body>
</html>