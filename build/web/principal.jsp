<%-- 
    Document   : Principal
    Created on : 04/11/2019, 22:18:55
    Author     : alunocmc
--%>

<%@page import="model.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Principal</title>
    </head>
    <body>
        <%
            //RECUPERA O USUARIO DA SESSAO
            Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
            if (usuario != null) {
        %>
        <h1>Bem Vindo,<%=usuario.getLogin()%></h1>
        <%}%>
        <a href="admin/cadastro_usuario.jsp">Área restrita</a><br/>
        <a href="/ControleAcesso?acao=Sair">Logout</a><br/>

    </body>
</html>
