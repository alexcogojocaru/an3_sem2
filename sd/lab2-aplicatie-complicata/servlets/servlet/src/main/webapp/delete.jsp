<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.03.2021
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Stergere student</title>
</head>
<body>
    <h3>Stergere student</h3>
    <form action="./delete-student" method="post">
        Nume: <input type="text" name="nume">
        <button type="submit">Trimite</button>
    </form>
</body>
</html>
