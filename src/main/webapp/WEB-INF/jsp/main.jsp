<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <base href="<%=basePath%>">
    <title>分销商管理</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/icon.css">
    <script type="text/javascript" src="static/plugin/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/jquery.easyui.min.js"></script>
</head>
<body>
    <div class="easyui-layout" style="height: 700px;">
        <div data-options="region:'north',split:true" style="height:50px;">分销商管理</div>
        <div data-options="region:'west',split:true" title="" style="width:200px;">
            <div class="easyui-accordion" style="width:200px;">
                <div title="用户" style="width:200px;">
                    <ul>
                        <li>用户管理</li>
                    </ul>
                </div>
            </div>
        </div>
        <div data-options="region:'center',title:'',iconCls:'icon-ok'">

        </div>
    </div>
</body>
</html>
