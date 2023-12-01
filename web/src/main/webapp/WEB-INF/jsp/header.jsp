<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
  <c:choose>
    <c:when test="${not empty sessionScope.user}">
      <form class="button-logout" action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Выход</button>
      </form>
    </c:when>
    <c:otherwise>
      <form class="button-login" action="${pageContext.request.contextPath}/login" method="get">
        <button type="submit">Авторизация</button>
      </form>

      <form class="button-registration" action="${pageContext.request.contextPath}/registration" method="get">
        <button type="submit">Регистрация</button>
      </form>
    </c:otherwise>
  </c:choose>
</div>

<style>
  .button-logout {
    position: absolute;
    top: 1%;
    left: 96%;
  }
  .button-login {
    position: absolute;
    top: 1%;
    left: 93%;
  }
  .button-registration {
    position: absolute;
    top: 1%;
    left: 86%;
  }
</style>