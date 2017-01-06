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
    <title>缺陷管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <script type="text/javascript">
        function clearInput() {
            $("input[name='loginId']").val('');
            $("input[name='userPassword']").val('');
        }
    </script>
</head>
<body>
    <form action="login/doLogin" method="post">
        <div style="margin: 120px 20px 20px 20px; text-align: center;">
            <span style="color: red">${status}</span>
        </div>
        <div style="margin: 20px 20px 20px 20px; text-align: center;">
            <label> 用户：<input type="text" name="loginName"/>
            </label>
        </div>
        <div style="margin: 20px; text-align: center;">
            <label> 密码：<input type="password" name="userPassword"/>
            </label>
        </div>
        <div style="margin: 20px; text-align: center;">
            <input type="submit" value="登录" style="margin: 10px"/>
            <input type="reset" value="清空" style="margin: 10px"/>
        </div>
    </form>
</body>
</html>
