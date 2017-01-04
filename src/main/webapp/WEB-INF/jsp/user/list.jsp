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
    <title>缺陷管理系统</title>
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
<script type="text/javascript">
    function query() {
        $('#dg').datagrid({
            queryParams: {
                loginId: $('#loginId').val(),
                userName: $('#userName').val(),
                deptNumber: $('#deptNumber').val(),
                deptName: $('#deptName').val(),
                loginType: $('#loginType').val(),
                enabled: $('#enabled').val()
            }
        });
    }
    function edit() {
        var checked = $('#dg').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择一条数据!', 'warning');
        } else if (l > 1) {
            $.messager.alert('警告', '只能同时编辑一条数据!', 'warning');
        } else {
            $('#queryForm').attr('action', 'user/edit');
            $('#queryForm input[name=id]').val(checked[0].id);
            $('#queryForm').submit();
            //location.href = '<%=basePath%>user/edit?id=' + checked[0].id;
        }
    }

    function fEnabled(value, row, index) {
        var val = "";
        if (value == 0) {
            val = "禁用";
        } else if (value == 1) {
            val = "启用";
        }
        return val;
    }

</script>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <table class="easyui-datagrid" id="dg"
               data-options="
		method:'post',
		url:'user/data',
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
                <th data-options="field:'loginName',width:100">登录账号</th>
                <th data-options="field:'userName',width:100">姓名</th>
                <th data-options="field:'mobile',width:150">手机号</th>
                <th data-options="field:'wechat',width:150">微信号</th>
                <th data-options="field:'enabled',width:80,formatter:fEnabled">是否启用</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="user/edit" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                   onclick="edit()">编辑</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="user/list" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>登录账号:</td>
                        <td><input type="text" id="loginName"></td>
                        <td>姓名:</td>
                        <td><input type="text" id="userName"></td>
                        <td>是否启用:</td>
                        <td>
                            <select id="enabled">
                                <option value=""></option>
                                <option value="0">禁用</option>
                                <option value="1">启用</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>