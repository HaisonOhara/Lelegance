<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conferir</title>
    </head>
    <body>
        <div>
            <h1>Endereco</h1>
         <p>${endereco.cidade}</p> 
         <p>${endereco.endereco}</p>
         <p>${endereco.numero}</p>
        </div>
        
        <div>
            <h1>Cartao</h1> 
         <p>${cartao.nomeCartao}</p>
         <p>${cartao.numero}</p>
        </div>
        <div>
         <h1>Resumo estilo</h1>
         <p>${estilo.nome}</p> 
         <p>${estilo.valor}</p>
         <p>${assi.numeroMeses}</p>
        </div>
        
        
        <h3>TOTAL: ${assi.total}</h3>
        
        <p><a href="assinar">Assinar</a></p>
    </body>
</html>
