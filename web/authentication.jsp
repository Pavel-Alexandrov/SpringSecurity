<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Я вас не знаю, пожалуйста, авторизуйтесь:</h3>
<form action="/auth" method="post">
    <p>Login</p>
    <p><input type="text" name="login"></p>
    <p>Password</p>
    <p><input type="text" name="password"></p>
    <p><input type="submit"></p>
</form>
</body>
</html>