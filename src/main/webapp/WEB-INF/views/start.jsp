<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Start</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Назовись!</h1>
    <form action="${pageContext.request.contextPath}/start" method="post">
        <input type="text" name="nickname" placeholder="Введите ваш никнейм" class="input-field">
        <button type="submit" class="button">Начать игру</button>
    </form>
</div>
</body>
</html>