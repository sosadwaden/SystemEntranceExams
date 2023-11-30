<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Ошибка</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
          crossorigin="anonymous">
</head>
<body>
    <div>
      <span>
        Упс. Произошла непредвиденная ситуация!
        Мы уже в курсе о проблеме и пытаемся её решить.
      </span>
      <br>
      <img src="${pageContext.request.contextPath}/resources/bug.png" alt="computer bug" width="256" height="256">
    </div>
</body>
</html>
