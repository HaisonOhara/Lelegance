<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Todos os estilos</title>
    </head>
    <body>
        <c:forEach var="e" items="${Estilos}">
            <div style="height: 300px; width: 200px;">
                <c:if test="${e.status== 'Ativo'}">
                    <p>${e.nome}</p>
                    <p>${e.id}</p>
                    <a href="buscaEstilo?id=${e.id}">Entrar</a> 
                </div>
            </c:if>
        </c:forEach>
    </body>
</html>
