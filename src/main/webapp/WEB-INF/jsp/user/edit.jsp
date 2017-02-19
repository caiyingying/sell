<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
    <script type="text/javascript" src="static/plugin/jquery-validate/jquery.validate.min-1.14.js"></script>
    <script type="text/javascript" src="static/plugin/easyui/local/easyui-lang-zh_CN.js"></script>
</head>
<body>
<shiro:hasPermission name="user:nav">
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="基本信息" style="padding:10px">
                <form id="form1" action="user/saveOrUpdate" method="post">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
                       onclick="add()">保存</a>
                    <input type="hidden" name="id" value="${user.id}" id="entityId"/>
                    <table width="100%">
                        <tr>
                            <td width="100">登录账号:</td>
                            <td><input type="text" name="loginName" value="${user.loginName}"></td>
                        </tr>
                        <tr>
                            <td>姓名:</td>
                            <td><input type="text" name="userName" value="${user.userName}"></td>
                        </tr>
                        <tr>
                            <td>手机号:</td>
                            <td><input type="text" name="mobile" value="${user.mobile}"></td>
                        </tr>
                        <tr>
                            <td>微信:</td>
                            <td><input type="text" name="wechat" value="${user.wechat}"></td>
                        </tr>
                        <tr>
                            <td>是否启用:</td>
                            <td>
                                <select name="enabled">
                                    <option value="0" ${user.enabled == 0 ? "selected" : ""}>禁用</option>
                                    <option value="1" ${user.enabled == 1 or empty user.enabled ? "selected" : ""}>启用
                                    </option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            <c:if test="${!empty user.id}">
                <div title="用户角色" style="padding:10px">
                    <table class="easyui-datagrid" id="dg"
                           data-options="
                                        method:'post',
                                        url:'userRole/roleData?userId=${user.id}',
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
                            <th data-options="field:'name',width:280">角色名称</th>
                            <th data-options="field:'description',width:420">角色描述</th>
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

                    <div id="w-search-role" class="easyui-window" title="角色查询"
                         data-options="iconCls:'icon-save',closed:true" style="width:600px;height:360px;padding:5px;">
                        <table id="dg-query-role"
                               data-options="
                                    method:'post',
                                    url:'role/data',
                                    noheader:true,
                                    fit:true,
                                    rownumbers:true,
                                    border:false,
                                    toolbar:'#tb-Query-role',
                                    onDblClickRow:clickSearchRow,
                                    pagination:true,
                                    pageSize:10,
                                    loadMsg:'请稍后...'
                                    ">
                            <thead>
                            <tr>
                                <th data-options="field:'ck',checkbox:true"></th>
                                <th data-options="field:'id',hidden:'true'"/>
                                <th data-options="field:'name',width:140">角色名称</th>
                                <th data-options="field:'description',width:140">角色描述</th>
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

<div id="tb-Query-role" style="display: none">
    <table>
        <tr>
            <td>角色名称</td>
            <td><input type="text" id="name"></td>
            <td>角色描述</td>
            <td><input type="text" id="description"></td>
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
                   onclick="queryRole()">查询</a>

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
        var node = $('#navTree').tree('find', 'user');
        $('#navTree').tree('select', node.target);
    });

    function query() {
        $('#dg').datagrid({
            queryParams: {
                loginId: $('#loginId').val(),
                userName: $('#userName').val(),
                deptNumber: $('#deptNumber').val(),
                deptName: $('#deptName').val()
            }
        });
    }

    function add() {
        /* 返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证 */
        var validate = $('#form1').validate({
            rules: {
                loginName: {
                    required: true,
                    remote: {
                        url: "user/vLoginId",     //后台处理程序
                        type: "post",               //数据发送方式
                        dataType: "json",           //接受数据格式
                        data: {                     //要传递的数据
                            id: '${user.id}'
                        }
                    }
                },
                userName: "required",
                enabled: "required"
            },
            messages: {
                loginName: {required: "必填字段!", remote: "账号已存在!"},
                userName: "必填字段!",
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
        $('#w-search-role').window('open');
        $('#dg-query-role').datagrid();
    }

    function queryRole() {
        $('#dg-query-role').datagrid({
            queryParams: {
                name: $('div#tb-Query-role #name').val(),
                description: $('div#tb-Query-role #description').val(),
                enabled: $('div#tb-Query-role #enabled').val()
            }
        });
    }
    function batchAdd() {
        var checked = $('#dg-query-role').datagrid('getChecked');
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
                url: 'userRole/saveBatchRole',
                data: {userId: '${user.id}', roleIds: ids},
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
                    url: 'userRole/deleteForUser',
                    data: {userId: '${user.id}', roleIds: ids},
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
            url: 'userRoleRelation/save',
            data: {userId: '${user.id}', roleId: row.id},
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
</shiro:hasPermission>
<shiro:lacksPermission name="user:nav">
    您没有权限访问，请联系管理员。
</shiro:lacksPermission>
</body>
</html>
