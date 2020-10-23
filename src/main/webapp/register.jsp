<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form action="/auth?action=register" method="post">
    <input type="text" name="login" placeholder="Логин">
    <br>
    <input type="password" name="password" placeholder="Пароль">
    <br>
    <input type="email" name="email" placeholder="E-mail">
    <br>
    <button type="submit">Зарегистрироваться</button>
    <button type="button" onclick="window.location.replace('/auth')">Вход</button>
</form>
</body>
</html>
