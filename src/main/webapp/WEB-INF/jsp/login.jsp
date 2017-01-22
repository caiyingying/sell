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
    <title>优达生物科技分销商管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <link rel="stylesheet" type="text/css" href="static/css/login.css">
    <script type="text/javascript">
        function clearInput() {
            $("input[name='loginId']").val('');
            $("input[name='userPassword']").val('');
        }
    </script>
</head>
<body>
<section class="container">
    <div class="login">
        <h1>优达生物科技</h1>
        <form method="post" action="login/doLogin">
            <p><input type="text" name="loginName" value="" placeholder="用户名"></p>
            <p><input type="password" name="userPassword" value="" placeholder="密码"></p>
            <!--
            <p class="remember_me">
                <label>
                    <input type="checkbox" name="remember_me" id="remember_me">
                    Remember me on this computer
                </label>
            </p>
            -->
            <p class="submit">
                <input type="submit" name="commit" value="登录">
                <input type="reset" name="commit" value="清空">
            </p>
        </form>
    </div>

    <div class="login-help">
        <p>${status}</p>
    </div>
    <!--
    <div class="login-help">
        <p>Forgot your password? <a href="index.html">Click here to reset it</a>.</p>
    </div>
    -->
</section>

    <!--
    <form action="login/doLogin" method="post">
        <div style="margin: 120px 20px 20px 20px; text-align: center;">
            <span style="color: red">${status}</span>
        </div>
        <div style="margin: 20px 20px 20px 20px; text-align: center;">
            <label> 用户：<input type="text" name="loginName" value="${user.loginName}"/>
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
    -->
</body>
</html>
