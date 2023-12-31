<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Факультет</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <%@include file="header.jsp" %>
    <h1>Информация о факультете</h1>
    <span>Имя факультета: ${requestScope.faculty.name}</span>
    <br>
    <span>Количество мест: ${requestScope.faculty.facultyCapacity}</span>
    <br>
    <img src="${pageContext.request.contextPath}/images/${requestScope.faculty.image}" height="400" width="400" alt="Picture not found">
    <br>
    <span>Описание факультета: ${requestScope.faculty.description}</span>
    <br>
    <a href="${pageContext.request.contextPath}/applicant?facultyId=${requestScope.faculty.id}">Список участников отбора</a>
    <br>
    <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'APPLICANT'}">
        <form action="${pageContext.request.contextPath}/faculty?facultyId=${requestScope.faculty.id}" method="post">
            <button type="submit">Отправить свою заявку</button>
        </form>
    </c:if>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <a href="${pageContext.request.contextPath}/update-faculty?facultyId=${requestScope.faculty.id}" class="link-button">Обновить информацию факультета</a>
        <form class="inline" action="${pageContext.request.contextPath}/delete-faculty?facultyId=${requestScope.faculty.id}" method="post">
            <button type="submit" class="link-button">Удалить факультет</button>
        </form>
    </c:if>
</body>
</html>

<style>
    .inline {
        display: inline;
    }

    .link-button {
        background: none;
        border: none;
        color: blue;
        text-decoration: underline;
        cursor: pointer;
        font-size: 1em;
        font-family: serif;
    }
    .link-button:focus {
        outline: none;
    }
    .link-button:active {
        color:red;
    }
</style>
