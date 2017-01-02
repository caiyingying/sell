<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
</head>
<body>
    <form action="login/doLogin" method="post">
        <div>
            <label>登录名</label><input type="text" name="loginId"/>
        </div>
        <div>
            <label>密码</label><input type="password" name="userPassword"/>
        </div>
        <div>
            <input type="submit" name="登录">
            <input type="reset" name="重置">
        </div>
    </form>
</body>
</html>
