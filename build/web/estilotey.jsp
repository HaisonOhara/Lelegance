<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Especifico</title>
    </head>
    <body>
        <form method="post" action="preAssinar">
            <div style="height: 300px; width: 200px;">
                <p>${estilo.descricao}</p>
                <p>${estilo.valor}</p>
                <select name="plano">
                    <option value="1">1</option>
                    <option value="3">3</option>
                    <option value="6">6</option>
                </select>
                <p><input type="text" name="id" value="${estilo.id}"</p>
                <p> <input type="submit" value="comprar"> </p>
            </div>
        </form>
    </body>
</html>
