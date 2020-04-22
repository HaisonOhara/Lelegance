<%-- 
    Document   : erro.jsp
    Created on : 04/11/2019, 22:12:45
    Author     : alunocmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error Page</title>
    </head>
    <body>
        <h1>Erro!</h1>
        <%= ((Exception) request.getAttribute("erro")).getMessage()%>
    </body>
</html>
