<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>Quest</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <c:if test="${quest.over && quest.win}">
        <h1 class="win-title">Победа!</h1>
        <a href="${pageContext.request.contextPath}/start" class="button">Играть снова</a>
    </c:if>
    <c:if test="${quest.over && !quest.win}">
        <h1 class="lose-title">Поражение!</h1>
        <p>${loseText}</p>
        <a href="${pageContext.request.contextPath}/start" class="button">Попробовать снова</a>
    </c:if>
    <c:if test="${!quest.over}">
        <h1>${quest.currentQuestion.text}</h1>
        <c:forEach var="answer" items="${quest.currentQuestion.answers}">
            <form action="${pageContext.request.contextPath}/answer" method="post">
                <input type="hidden" name="answerId" value="${answer.id}">
                <button type="submit" class="button">${answer.text}</button>
            </form>
        </c:forEach>
    </c:if>
</div>
</body>
</html>