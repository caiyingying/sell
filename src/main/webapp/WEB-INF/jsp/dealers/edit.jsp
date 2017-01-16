<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <script type="text/javascript" src="static/plugin/jquery-validate/jquery.validate.min-1.14.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/local/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="static/js/jquery.qrcode.min.js"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="基本信息" style="padding:10px">
                <form id="form1" action="dealers/saveOrUpdate" method="post">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
                       onclick="add()">保存</a>
                    <input type="hidden" name="id" value="${dealers.id}" id="entityId"/>
                    <table width="100%">
                        <tr>
                            <td width="100">名称:</td>
                            <td><input type="text" name="name" value="${dealers.name}"></td>
                        </tr>
                        <tr>
                            <td>描述:</td>
                            <td><input type="text" name="description" value="${dealers.description}"></td>
                        </tr>
                        <tr>
                            <td>电话:</td>
                            <td><input type="text" name="phone" value="${dealers.phone}"></td>
                        </tr>
                        <tr>
                            <td>地址:</td>
                            <td><input type="text" name="address" value="${dealers.address}"></td>
                        </tr>
                        <tr>
                            <td>是否启用:</td>
                            <td>
                                <select name="enabled">
                                    <option value="0" ${dealers.enabled == 0 ? "selected" : ""}>禁用</option>
                                    <option value="1" ${dealers.enabled == 1 or empty dealers.enabled ? "selected" : ""}>启用
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>二维码:</td>
                            <td>
                                <div id="qrcode" style="margin: 20px 0px;"></div>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <c:if test="${!empty dealers.id}">
                <div title="权限用户" style="padding:10px">
                    <table class="easyui-datagrid" id="dg"
                           data-options="
                                        method:'post',
                                        url:'dealers/userData?dealersId=${dealers.id}',
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
                            <th data-options="field:'loginName',width:280">登录账号</th>
                            <th data-options="field:'userName',width:420">姓名</th>
                            <th data-options="field:'enabled',width:80,formatter:fEnabled">状态</th>
                        </tr>
                        </thead>
                    </table>

                    <div id="tb" style="height:auto">
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
                        <a href="javascript:void(0)" class="easyui-linkbutton"
                           data-options="iconCls:'icon-remove',plain:true" onclick="rm()">删除</a>
                    </div>

                    <div id="w-search-user" class="easyui-window" title="用户查询"
                         data-options="iconCls:'icon-save',closed:true" style="width:600px;height:360px;padding:5px;">
                        <table id="dg-query-user"
                               data-options="
                                    method:'post',
                                    url:'user/data',
                                    noheader:true,
                                    fit:true,
                                    rownumbers:true,
                                    border:false,
                                    toolbar:'#tb-Query-user',
                                    onDblClickRow:clickSearchRow,
                                    pagination:true,
                                    pageSize:10,
                                    loadMsg:'请稍后...'
                                    ">
                            <thead>
                            <tr>
                                <th data-options="field:'ck',checkbox:true"></th>
                                <th data-options="field:'id',hidden:'true'"/>
                                <th data-options="field:'loginName',width:140">登录账号</th>
                                <th data-options="field:'userName',width:140">姓名</th>
                                <th data-options="field:'enabled',width:100,formatter:fEnabled">状态</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div id="tb-Query-user" style="display: none">
    <table>
        <tr>
            <td>登录账号</td>
            <td><input type="text" id="loginName"></td>
            <td>用户姓名</td>
            <td><input type="text" id="userName"></td>
        </tr>
        <tr>
            <td>是否启用:</td>
            <td>
                <select id="enabled">
                    <option value=""></option>
                    <option value="0">禁用</option>
                    <option value="1">启用</option>
                </select>
            </td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" plain="true"
                   onclick="queryUser()">查询</a>

            </td>
            <td>
                <a href="javascript:void(0)" class="easyui-linkbutton" plain="true" onclick="batchAdd()">添加</a>
            </td>
        </tr>
    </table>
</div>
<script type="application/javascript">
    //选中菜单
    $(function(){
        var node = $('#navTree').tree('find', 'dealers');
        $('#navTree').tree('select', node.target);

        //生成URL
        $('#qrcode').qrcode("${dealers.codeUrl}");
    });

    function add() {
        /* 返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证 */
        var validate = $('#form1').validate({
            rules: {
                name: "required",
                enabled: "required"
            },
            messages: {
                name: "必填字段!",
                enabled: "必填字段"
            },
            highlight: function (element) {
// 				$(element).parent('td').find('label').before('<br>');
            },
            success: function (element) {
            }
        });
        if (validate.form()) {
            $('#form1').submit();
        }
    }

    function append() {
        $('#w-search-user').window('open');
        $('#dg-query-user').datagrid();
    }

    function queryUser() {
        $('#dg-query-user').datagrid({
            queryParams: {
                loginName: $('div#tb-Query-user #loginName').val(),
                userName: $('div#tb-Query-user #userName').val(),
                enabled: $('div#tb-Query-user #enabled').val()
            }
        });
    }
    function batchAdd() {
        var checked = $('#dg-query-user').datagrid('getChecked');
        var l = checked.length;
        if (l == 0) {
            $.messager.alert('警告', '请选择数据!', 'warning');
        } else {
            var ids = "";
            for (var i = 0; i < l; i++) {
                var row = checked[i];
                ids += row.id + ";";
            }
            $.ajax({
                type: 'post',
                url: 'dealers/saveBatchUser',
                data: {dealersId: '${dealers.id}', userIds: ids},
                success: function (data) {
                    if (!data.success) {
                        $.messager.alert('警告', data.reason, 'ERROR');
                    } else {
                        $.messager.alert('提示', "添加成功!");
                        $('#dg').datagrid('reload');
                    }
                }
            });
        }
    }

    function rm() {
        var checked = $('#dg').datagrid('getChecked');
        var ids = "";
        for (var i = 0; i < checked.length; i++) {
            ids += checked[i].id + ",";
        }
        if (!ids) {
            $.messager.alert('警告', "请选择一条数据!", 'ERROR');
            return;
        }
        $.messager.confirm("操作提示", "您确定要执行删除吗？", function (data) {
            if (data) {
                $.ajax({
                    type: 'post',
                    url: 'dealers/deleteUsers',
                    data: {dealersId: '${dealers.id}', userIds: ids},
                    success: function (data) {
                        if (!data.success) {
                            $.messager.alert('警告', data.reason, 'ERROR');
                        } else {
                            $.messager.alert('提示', "删除成功!");
                        }
                        $('#dg').datagrid('reload');
                    }
                });
            }
        });

    }

    function clickSearchRow(index, row) {
        $.ajax({
            type: 'post',
            url: 'dealers/saveRelUser',
            data: {dealersId: '${dealers.id}', userId: row.id},
            success: function (data) {
                if (!data.success) {
                    $.messager.alert('警告', data.reason, 'ERROR');
                } else {
                    $.messager.alert('警告', '添加成功!');
                }
                $('#dg').datagrid('reload');
            }
        });
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
</body>
</html>
