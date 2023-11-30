<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Админ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <h1>Админ страница</h1>
    <h2>Создать факультет:</h2>
    <form action="${pageContext.request.contextPath}/create-faculty" method="post" enctype="multipart/form-data">
        <span>Имя:</span>
        <label for="name">
            <input type="text" name="name" id="name" required>
        </label>
        <br>
        <span>Количество мест:</span>
        <label for="faculty_capacity">
            <input type="text" name="faculty_capacity" id="faculty_capacity" required>
        </label>
        <br>
        <span>Описание факультета:</span>
        <br>
        <label for="description">
<%--            <input type="text" name="description" id="description">--%>
            <textarea name="description" id="description" cols="65" rows="10" required></textarea>
        </label>
        <br>
        <span>Картинка факультета:</span>
        <label for="image">
            <input type="file" name="image" id="image">
        </label>
        <br>
        <button type="submit">Создать</button>
        <c:if test="${not empty requestScope.errors}">
            <div style="color: red">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.message}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>
    </form>
</body>
</html>
