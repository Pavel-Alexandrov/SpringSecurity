<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить пользователя</title>
</head>
<body>
<form action="/admin/add" method="post">
    <p>Login</p>
    <p><input type="text" name="login"></p>
    <p>Name</p>
    <p><input type="text" name="name"></p>
    <p>Password</p>
    <p><input type="text" name="password"></p>

    <p>
        <select required, name="access">
            <option>admin</option>
            <option>user</option>
        </select>
    </p>
    <p><input type="submit"></p>
</form>
</body>
</html>