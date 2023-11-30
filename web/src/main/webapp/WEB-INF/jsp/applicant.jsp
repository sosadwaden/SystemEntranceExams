<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Кандидаты</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
        crossorigin="anonymous">
</head>
<body>
  <c:choose>
    <c:when test="${not empty requestScope.applicants}">
      <c:if test="${sessionScope.user.role == 'TEACHER'}">
        <form action="${pageContext.request.contextPath}/applicant?facultyId=${requestScope.facultyId}" method="post">
          <button type="submit">Выставить результат</button>
        </form>
      </c:if>
      <h1>Кандидаты:</h1>
      <ul>
        <c:forEach var="applicant" items="${requestScope.applicants}">
          <li>
            <span>Имя: ${applicant.name}</span>
            <br>
            <span>Фамилия: ${applicant.surname}</span>
            <br>
            <span>Дата рождения: ${applicant.birthday}</span>
            <br>
            <span>Баллы: ${applicant.score}</span>
            <br>
            <span>Статус: ${applicant.status}</span>
          </li>
        </c:forEach>
      </ul>
    </c:when>
    <c:otherwise>
      <span>Ещё никого нет</span>
    </c:otherwise>
  </c:choose>
</body>
</html>
