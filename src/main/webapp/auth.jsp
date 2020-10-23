<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<form method="post">
    <input type="text" name="login" placeholder="Логин">
    <br>
    <input type="password" name="password" placeholder="Пароль">
    <br>
    <button type="submit">Войти</button>
    <button type="button" onclick="window.location.replace('/auth?action=register')">Регистрация</button>
</form>
</body>
</html>
