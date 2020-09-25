<%-- 
    Document   : login
    Created on : 25/09/2020, 03:02:20
    Author     : ocris
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Bookshop Website</title>
</head>
<body>
    <div style="text-align: center">
        <h1>Admin Login</h1>
        <form action="login" method="post">
            <label for="usuario">Email:</label>
            <input name="usuario" size="30" />
            <br><br>
            <label for="senha">Password:</label>
            <input type="senha" name="senha" size="30" />
            <br>${message}
            <br><br>           
            <button type="submit">Login</button>
        </form>
    </div>
</body>
</html>