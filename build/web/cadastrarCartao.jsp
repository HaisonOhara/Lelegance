<%-- 
    Document   : cadastrarCartao
    Created on : 17/04/2020, 16:35:07
    Author     : joaov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>cadastrarCartao</title>
    </head>
    <body>
        <form method="post" action="CadastrarCartao">
            <p> <input type="text" name="nomeCartao"value="" placeholder="nomeCartao"></p>
            <p><input type="text" name="numeroCartao"value=""placeholder="numeroCartao"></p>
            <p> <input type="text" name="vencimento"value=""placeholder="vencimento"></p>
            <p><input type="text" name="cnn"value=""placeholder="cnn"></p>
            <p><input type="submit" name="Cadastrar" value="cadastrar"></p>  
        </form>
    </body>
</html>
