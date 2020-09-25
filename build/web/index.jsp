<%-- 
    Document   : login
    Created on : 25/09/2020, 03:02:20
    Author     : ocris
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<%
    session = request.getSession(false);
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(application.getContextPath() + "/login");
    } else {
        response.sendRedirect(application.getContextPath() + "/principal");
    }

%> 
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <div>TODO write content mudei o conteudo</div>
        <div>Dados:
            <b>${usuario.nome} (${usuario.login})</b>
        </div>
    </body>
</html>
