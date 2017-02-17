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
<script type="text/javascript">
    //选中菜单
    $(function(){
        var node = $('#navTree').tree('find', 'product');
        $('#navTree').tree('select', node.target);

        $('#dg').datagrid({
            onDblClickRow: function (index,row) {//双击编辑
                $('#queryForm').attr('action', 'product/edit');
                $('#queryForm input[name=id]').val(row.id);
                $('#queryForm').submit();
            }
        });
    });

    function query() {
        $('#dg').datagrid({
            queryParams: {
                productName: $('#productName').val()
            }
        });
    }

    /**
     * 同步产品
     */
    function sync() {
        $.ajax({
            type: 'post',
            url: 'product/sync',
            success: function (data) {
                if (!data.success) {
                    $.messager.alert('警告', data.reason, 'ERROR');
                } else {
                    $.messager.alert('提示', "同步产品成功!");
                    $('#dg').datagrid('reload');
                }
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
            $('#queryForm').attr('action', 'product/edit');
            $('#queryForm input[name=id]').val(checked[0].id);
            $('#queryForm').submit();
        }
    }
</script>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <table class="easyui-datagrid" id="dg"
               data-options="
		method:'post',
		url:'product/data',
		noheader:true,
		fit:true,
		rownumbers:true,
		border:false,
		toolbar:'#tb',
		pagination:true,
		pageSize:30,
		loadMsg:'请稍后...'
		">
            <thead>
            <tr>
                <th data-options="field:'ck',checkbox:true"></th>
                <th data-options="field:'id',hidden:'true'"/>
                <th data-options="field:'productId',width:100">产品ID</th>
                <th data-options="field:'skuId',width:100">SKU</th>
                <th data-options="field:'productName',width:250">产品名称</th>
                <th data-options="field:'skuTitle',width:100">产品型号</th>
                <th data-options="field:'productPrice',width:100">产品价格</th>
                <th data-options="field:'discount',width:100">折扣</th>
            </tr>
            </thead>
        </table>

        <div id="tb">
            <div style="margin-bottom:5px">
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-reload" plain="true"
                   onclick="sync()">同步产品</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true"
                   onclick="edit()">编辑</a>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="query()">查询</a>
            </div>
            <form action="product/list" method="post" id="queryForm">
                <input type="hidden" name="id" value=""/>
                <table>
                    <tr>
                        <td>产品名称:</td>
                        <td><input type="text" id="productName"></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>