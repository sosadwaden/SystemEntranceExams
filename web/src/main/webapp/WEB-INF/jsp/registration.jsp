<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
    <link rel="stylesheet" href="../../resources/bootstrap.min.css">

</head>
<body>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <h1 style="font-size: 24px">Заполните данные:</h1>
        <span>Имя:</span>
        <br>
        <label for="name">
            <input type="text" name="name" id="name" value="${param.name}" required>
        </label>
        <br>
        <span>Фамилия:</span>
        <br>
        <label for="surname">
            <input type="text" name="surname" id="surname" value="${param.surname}" required>
        </label>
        <br>
        <span>Дата рождения:</span>
        <br>
        <label for="birthday">
            <input type="date" name="birthday" id="birthday" value="${param.birthday}" required>
        </label>
        <br>
        <label for="role">Ваша роль:
            <br>
            <select name="role" id="role">
                <c:forEach var="role" items="${requestScope.roles}">
                    <option value="${role}">${role}</option>
                </c:forEach>
            </select>
        </label>
        <br>
        <span>Почта:</span>
        <br>
        <label for="email">
            <input type="text" name="email" id="email" value="${param.email}" required>
        </label>
        <br>
        <span>Пароль:</span>
        <br>
        <label for="password">
            <input type="password" name="password" id="password" value="${param.password}" required>
        </label>
        <br>
        <button type="submit">Регистрация</button>
        <c:if test="${not empty requestScope.login_error}">
            <div style="color: red">
                <span>${requestScope.login_error.message}</span>
                <br>
            </div>
        </c:if>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
        <br>
        <a href="/login">Уже есть аккаунт?</a>
    </form>
</body>
</html>
