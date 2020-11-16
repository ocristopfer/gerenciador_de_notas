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

        <!-- Custom styles for this template -->
        <link href="index.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
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
                    </li>
                </ul>

            </div>
        </nav>

        <main role="main" class="container">

            <div class="starter-template">
                <h1>Pagina do Professor</h1>


                <div id="listaNotas">
                    <div class="m-3">
                        <input type="text" v-model="matriculaBusca" placeholder="Buscar Aluno por Matricula" aria-label="Search">
                        <button class="btn btn-outline-success btn-sm my-2"  v-on:click="buscarAluno(this)" >Buscar</button>
                    </div> 
                    <table>
                        <thead>
                            <tr>
                                <td>Materia</td>
                                <td>Av1</td>
                                <td>Aps1</td>
                                <td>Av2</td>
                                <td>Aps2</td>
                                <td>Av3</td>
                                <td></td>
                            </tr>
                        </thead>
                        <tr v-for="nota in notas">
                            <td  style="width:200px !important">
                                <span v-show="!nota.edit">{{nota.materia}}</span>
                                <input type="text" v-model="nota.materia" v-show="nota.edit">
                            </td>
                            <td  style="width:120px !important">
                                <span v-show="!nota.edit">{{nota.av1}}</span>
                                <input type="text" v-model="nota.av1" v-show="nota.edit">
                            </td>
                            <td  style="width:120px">
                                <span v-show="!nota.edit">{{nota.aps1}}</span>
                                <input type="text" v-model="nota.aps1" v-show="nota.edit">
                            </td>

                            <td  style="width:120px">
                                <span v-show="!nota.edit">{{nota.av2}}</span>
                                <input type="text" v-model="nota.av2" v-show="nota.edit">
                            </td>

                            <td  style="width:120px">
                                <span v-show="!nota.edit">{{nota.aps2}}</span>
                                <input type="text" v-model="nota.aps2" v-show="nota.edit">
                            </td>

                            <td  style="width:120px">
                                <span v-show="!nota.edit">{{nota.av3}}</span>
                                <input type="text" v-model="nota.av3" v-show="nota.edit">
                            </td>


                            <td  style="width:300px !important">
                                <button class="btn btn-primary btn-sm" v-show="!nota.edit" v-on:click="editarNota(nota)" >
                                    Editar</button>
                                <button class="btn btn-primary btn-sm" v-show="nota.edit" v-on:click="salvarEdicao(nota)">Salvar</button>
                                <button class="btn btn-danger btn-sm" v-show="nota.edit" v-on:click="cancelarEdicao(nota)">Cancelar</button>
                            </td>
                        </tr>
                    </table>

                </div>


            </div>

        </main><!-- /.container -->

        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="../plugins/jquery/js/jquery-3.5.1.min.js"></script>
        <script src="../plugins/jquery/js/popper.min.js"></script>
        <script src="../plugins/bootstrap/js/bootstrap.min.js"></script>
        <script src="../plugins/vue/js/vue.min.js"></script>
        <script src="../plugins/apiGatewayService.js"></script>
        <script src="./index.js"></script>  
    </body>
</html>

