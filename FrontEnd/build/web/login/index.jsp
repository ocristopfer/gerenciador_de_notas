<%-- 
    Document   : login
    Created on : 25/09/2020, 03:02:20
    Author     : ocris
--%>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../../../favicon.ico">

    <title>Login</title>

    <!-- Bootstrap core CSS -->
    <link href="../plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="login.css" rel="stylesheet">
  </head>

  <body class="text-center">
    <form class="form-signin" action="<%=application.getContextPath()%>/loginUser" method="post">
      <img class="mb-4" src="https://getbootstrap.com/assets/brand/bootstrap-solid.svg" alt="" width="72" height="72">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="usuario" class="sr-only">Usuario</label>
      <input type="" id="usuario" name="usuario" class="form-control" placeholder="Login" required autofocus>
      <label for="senha" class="sr-only">Senha</label>
      <input type="password" id="senha" name="senha" class="form-control" placeholder="Senha" required>
      <div class="checkbox mb-3">
        <!--label>
          <input type="checkbox" value="remember-me"> Remember me
        </label-->
      </div>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Logar</button>
      <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
    <script src="../plugins/jquery/js/jquery-3.5.1.min.js"></script>
    <script src="../plugins/jquery/js/popper.min.js"></script>
    <script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>