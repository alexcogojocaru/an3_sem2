<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.03.2021
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Actualizare curs</title>
</head>
<body>
<h3>Actualizare curs</h3>
<form action="./update-course" method="post">
    Nume : <input type="text" name="nume">
    Studenti: <input type="number" name="studs">
    <button type="submit">Submit</button>
</form>
</body>
</html>
