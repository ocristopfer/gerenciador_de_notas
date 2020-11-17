<%-- 
    Document   : principal
    Created on : 25/09/2020, 03:07:42
    Author     : ocris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    /* session = request.getSession(false);
    if (session.getAttribute("usuario") == null) {
        response.sendRedirect(application.getContextPath() + "/login");
    }*/

%>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="../../../../favicon.ico">

        <title>Starter Template for Bootstrap</title>

        <!-- Bootstrap core CSS -->
        <link href="../plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="../plugins/datatables/jquery.dataTables.css">

        <!-- Custom styles for this template -->
        <link href="index.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Boletim</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <!--li class="nav-item active">
                      <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" href="#">Link</a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link disabled" href="#">Disabled</a>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link dropdown-toggle" href="http://example.com" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                      <div class="dropdown-menu" aria-labelledby="dropdown01">
                        <a class="dropdown-item" href="#">Action</a>
                        <a class="dropdown-item" href="#">Another action</a>
                        <a class="dropdown-item" href="#">Something else here</a>
                      </div>
                    </li-->
                </ul>
                <button class="btn btn-outline-danger my-2 my-sm-0" id="logout">Logout</button>

            </div>
        </nav>

        <main role="main" class="container">

            <div class="starter-template">

                <h3>Notas do Aluno: </h3>
                <h4 id="nomeAluno"></h4>
                <table id="dtAluno">
                    <thead>
                        <tr>
                            <td>Materia</td>
                            <td>Av1</td>
                            <td>Aps1</td>
                            <td>Av2</td>
                            <td>Aps2</td>
                            <td>Av3</td>
                            <td>Média</td>
                            <td>Resultado</td>
                        </tr>
                    </thead>

                </table>

            </div>

        </main><!-- /.container -->

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->

        <script src="../plugins/jquery/js/jquery-3.5.1.min.js"></script>
        <script src="../plugins/jquery/js/popper.min.js"></script>
        <script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
        <script type="text/javascript" charset="utf8" src="../plugins/datatables/jquery.dataTables.js"></script>
        <script src="../plugins/apiGatewayService.js"></script>
        <script src="./index.js"></script>  
    </body>
</html>

