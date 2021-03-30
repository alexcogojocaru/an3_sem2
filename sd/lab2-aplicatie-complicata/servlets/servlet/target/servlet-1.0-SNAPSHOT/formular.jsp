<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 06.03.2021
  Time: 14:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Formular student</title>
    <meta charset="UTF-8">
</head>
<body>
    <h3>Formular student</h3>
    Introduceti datele despre student:
    <form action="./process-student" method="post">
        Nume: <input type="text" name="nume" /><br />
        Prenume: <input type="text" name="prenume" /><br />
        Varsta: <input type="number" name="varsta" /><br />
        <br />
        <button type="submit" name="submit">Trimite</button>
    </form>
</body>
</html>
