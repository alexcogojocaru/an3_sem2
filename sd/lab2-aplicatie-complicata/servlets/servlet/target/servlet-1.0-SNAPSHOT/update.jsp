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
    <title>Actualizare student</title>
</head>
<body>
    <h3>Actualizare student</h3>
    <form action="./update-student" method="post">
        Nume : <input type="text" name="nume">
        Nume nou: <input type="text" name="nume-nou">
        Prenume nou: <input type="text" name="prenume">
        Varsta noua: <input type="number" name="varsta">
        <button type="submit">Submit</button>
    </form>
</body>
</html>
