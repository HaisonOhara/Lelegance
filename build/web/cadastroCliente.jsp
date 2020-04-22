<%-- 
    Document   : cadastro_usuario
    Created on : 04/11/2019, 21:26:14
    Author     : alunocmc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Area Restrita</title>
    </head>
    <body>
        <h2>Novo por aqui? Cadastre-se</h2>
        
        <%
        String msg= (String) request.getAttribute("msg");
        if(msg !=null){
        %>
        
        <font color="blue"><%=msg %> </font>
        <%}%>
        <form action="/ControleUsuario" method="POST">
            Email:<input type ="text" name="txtLogin"><br/>
            Senha:<input type ="password" name="txtSenha"><br/>
            <input type="hidden" name="optPerfil" value="CLIENTE" >
            <input type="submit" value="Cadastrar" name="acao">
        </form>
        <a href="../index.jsp">Voltar</a>
    </body>
</html>
