<%-- 
    Document   : CadastrarEndereco
    Created on : 17/04/2020, 16:22:29
    Author     : joaov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Endereco</title>
    </head>
    <body>
        <form method="post" action="CadastrarEndereco">
            <p> <input type="text" name="cep"value="" placeholder="cep..."></p>
            <p><input type="text" name="cidade"value=""placeholder="cidade"></p>
            <p> <input type="text" name="estado"value=""placeholder="estado"></p>
            <p><input type="text" name="complemento"value=""placeholder="complemento"></p>
            <p><input type="text" name="endereco"value=""placeholder="endereco"></p>
            <p><input type="number" name="numero"value="" placeholder="numero"></p>
            <p><input type="submit" name="Cadastrar" value="cadastrar"></p>  
        </form>
    </body>
</html>
