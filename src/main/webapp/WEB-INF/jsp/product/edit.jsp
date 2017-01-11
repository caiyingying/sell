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
</head>
<body>
<div class="easyui-layout" data-options="fit:true">
    <%@ include file="/WEB-INF/jsp/common/titleNav.jsp" %>
    <%@ include file="/WEB-INF/jsp/common/sidebarNav.jsp" %>
    <div data-options="region:'center'">
        <div id="tabs" class="easyui-tabs" data-options="fit:true,border:false">
            <div title="产品信息" style="padding:10px">
                <form id="form1" action="product/update" method="post">
                    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-save" plain="true"
                       onclick="add()">保存</a>
                    <input type="hidden" name="id" value="${product.id}" id="entityId"/>
                    <table width="100%">
                        <tr>
                            <td width="100">产品名称:</td>
                            <td>${product.productName}</td>
                        </tr>
                        <tr>
                            <td>产品价格:</td>
                            <td>${product.productPrice}</td>
                        </tr>
                        <tr>
                            <td>折扣:</td>
                            <td><input type="text" name="discount" value="${product.discount}"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="application/javascript">
    //选中菜单
    $(function(){
        var node = $('#navTree').tree('find', 'product');
        $('#navTree').tree('select', node.target);
    });

    function add() {
        /* 返回的是一个validate对象，这个对象有一个form方法，返回的是是否通过验证 */
        var validate = $('#form1').validate({
            rules: {
                discount: "required"
            },
            messages: {
                discount: "必填字段"
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
</script>
</body>
</html>
