<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>优达生物科技分销商管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/plugin/easyui/themes/icon.css">
    <script type="text/javascript" src="static/js/jquery.min-1.11.3.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/local/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/plugin/jquery-form/jquery.form.js"></script>
</head>
<body>
<shiro:hasPermission name="customer:nav">
<script type="text/javascript">
    //选中菜单
    $(function () {
        var node = $('#navTree').tree('find', 'customer');
        $('#navTree').tree('select', node.target);
    });

    function query() {
        $('#dg').datagrid({
            queryParams: {
                nick: $('#nick').val(),
                businessId: $('#businessId').combobox("getValue")
                //businessName: $('#businessName').val()
            }
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <table class="easyui-datagrid" id="dg"
               data-options="
		method:'post',
		url:'customer/data',
		noheader:true,
		fit:true,
		rownumbers:true,
		border:false,
		toolbar:'#tb',
		pagination:true,
		pageSize:10,
		loadMsg:'请稍后...'
		">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id',hidden:'true'"/>
                <th data-options="field:'nick',width:100">微信昵称</th>
                <th data-options="field:'phone',width:100">手机号码</th>
                <th data-options="field:'businessName',width:200">分销商</th>
                <th data-options="field:'enableDateStr',width:150">生效时间</th>
                <th data-options="field:'disableDateStr',width:150">失效时间</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="customer/list" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>昵称:</td>
                        <td><input type="text" id="nick"/></td>
                        <td>分销商:</td>
                        <td><input class="easyui-combobox" id="businessId" data-options="
                            url:'dealers/selectData',
                            method:'post',
                            valueField:'code',
                            textField:'name',
                            panelHeight:'auto'
                            ">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</shiro:hasPermission>
<shiro:lacksPermission name="customer:nav">
    您没有权限访问，请联系管理员。
</shiro:lacksPermission>
</body>
</html>