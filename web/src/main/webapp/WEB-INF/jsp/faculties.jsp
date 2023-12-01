<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Факультеты</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <%@include file="header.jsp"%>
    <c:if test="${sessionScope.user.role == 'ADMIN'}">
        <form action="${pageContext.request.contextPath}/create-faculty">
            <input type="submit" value="Создать факультет"/>
        </form>
    </c:if>
    <h1>Список факультетов:</h1>
    <ul>
        <c:forEach var="faculty" items="${requestScope.faculties}">
            <li>
                <a href="${pageContext.request.contextPath}/faculty?facultyId=${faculty.id}">${faculty.name}</a>
            </li>
        </c:forEach>
    </ul>
</body>
</html>
