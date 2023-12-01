<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Аутентификация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <form action="${pageContext.request.contextPath}/login" method="post">
        <label for="email">Почта:
            <input type="text" name="email" id="email" value="${param.email}" required>
        </label>
        <br>
        <label for="password">Пароль:
            <input type="password" name="password" id="password" value="${param.password}" required>
        </label>
        <br>
        <button type="submit">Авторизоваться</button>
        <br>
        <a href="${pageContext.request.contextPath}/registration">
            <button type="button">Регистрация</button>
        </a>
        <c:if test="${param.error != null}">
            <div style="color: red">
                <span>Почта или пароль некорректны</span>
            </div>
        </c:if>
    </form>
</body>
</html>
