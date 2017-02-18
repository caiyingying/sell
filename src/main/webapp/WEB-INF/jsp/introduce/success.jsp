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
    <script type="text/javascript">
        function closeWindow() {
            WeixinJSBridge.invoke('closeWindow',{},function(res){
                //alert(res.err_msg);
            });
        }
    </script>
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
<div style="text-align: center;margin-top: 50px;">
        <div style="text-align: center;;">
            <label>绑定成功</label>
        </div>
        <div style="margin-top: 20px;">
            <input type="button" value="完成" onclick="closeWindow();" style="width: 500px; height: 80px; "/>
        </div>
</div>
</body>
</html>