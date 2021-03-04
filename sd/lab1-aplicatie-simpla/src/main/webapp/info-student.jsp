<%--
  Created by IntelliJ IDEA.
  User: alex
  Date: 28.02.2021
  Time: 23:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns:jsp="http://java.sun.com/JSP/Page">
<head>
    <title>Informatii student</title>
</head>
<body>
    <h3>Informatii student</h3>

<%--    populare bean cu informatii din cererea http (care vin din ProcessStudentServlet)--%>
    <jsp:useBean id="studentBean" class="beans.StudentBean">
        <jsp:setProperty name="studentBean" property="nume" value='<%= request.getAttribute("nume") %>'/>
        <jsp:setProperty name="studentBean" property="prenume" value='<%= request.getAttribute("prenume") %>'/>
        <jsp:setProperty name="studentBean" property="varsta" value='<%= request.getAttribute("varsta") %>'/>
        <jsp:setProperty name="studentBean" property="grupa" value='<%= request.getAttribute("grupa") %>'/>
        <jsp:setProperty name="studentBean" property="medie" value='<%= request.getAttribute("medie") %>' />
    </jsp:useBean>
<%--    folosirea bean-ului pentru afisarea informatiilor--%>
    <p>Urmatoarele informatii au fost introduse</p>
    <ul type="bullet">
        <li>Nume: <jsp:getProperty name="studentBean" property="nume" /></li>
        <li>Prenume: <jsp:getProperty name="studentBean" property="prenume"/></li>
        <li>Varsta: <jsp:getProperty name="studentBean" property="varsta"/></li>
        <li>Grupa: <jsp:getProperty name="studentBean" property="grupa"/></li>
        <li>Medie: <jsp:getProperty name="studentBean" property="medie"/></li>
        <li>Anul nasterii: <%
            Object anNastere = request.getAttribute("anNastere");

            if (anNastere != null) {
                out.print(anNastere);
            }
            else {
                out.print("necunoscut");
            }
        %></li>
    </ul>
</body>
</html>
