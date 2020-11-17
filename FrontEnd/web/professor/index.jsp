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
        <link href="./index.css" rel="stylesheet">
    </head>

    <body>

        <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
            <a class="navbar-brand" href="#">Lançar Notas</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExampleDefault">
                <ul class="navbar-nav mr-auto">
                    <!--<li class="nav-item active">
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
                    </li>-->
                </ul>
                <button class="btn btn-outline-danger my-2 my-sm-0" id="logout">Logout</button>
            </div>
        </nav>

        <main role="main" class="container">

            <div class="starter-template">
                <h1>Pagina do Professor</h1>
                <div id="listaNotas">
                    
                    <div class="m-3">
                        <input style="width: 200px" type="text" v-model="matriculaBusca" placeholder="Buscar Aluno por Matricula" aria-label="Search">
                        <button class="btn btn-outline-success btn-sm my-2"  v-on:click="buscarAluno(this)" >Buscar</button>
                    </div> 
                    
                    <h4>Notas do Aluno: {{nomeAluno}}</h4>
                    
                    <div style="float:left" class="mb-3">
                        <button class="btn btn-primary btn-sm"  v-on:click="adicionarNota()" >Lançar nova Nota</button>
                    </div>
                    
                    <table id="tbNotas" class="table">
                        <thead>
                            <tr>
                                <td style="width:200px !important">Materia</td>
                                <td class="spaceTituloDt">Av1</td>
                                <td class="spaceTituloDt">Aps1</td>
                                <td class="spaceTituloDt">Av2</td>
                                <td class="spaceTituloDt">Aps2</td>
                                <td class="spaceTituloDt">Av3</td>
                                <td style="width:300px"></td>
                            </tr>
                        </thead>
                        <tr v-for="nota in notas">
                            <td  style="width:200px !important">
                                <span v-show="!nota.novaNota">{{nota.materia}}</span>
                                <select v-show="nota.novaNota" v-model="disciplinaSelecionada" @change="atualizarDisciplina(nota)">
                                    <option value="" disabled selected>Escolha uma Disciplina</option>
                                    <option
                                        v-for="disciplina in listaDisciplinas" v-bind:value="{ id: disciplina.idDisciplina, text: disciplina.nomeDisciplina }" >
                                        {{ disciplina.nomeDisciplina }}
                                   </option>
                                  </select>
                            </td>
                            <td class="tamanhoValor">
                                <span  v-show="!nota.edit">{{nota.av1}}</span>
                                <input class="tamanhoEdicao" v-model="nota.av1" v-show="nota.edit" max="7" @change="valorMaximo(nota,'av1',7)">
                            </td > 
                            <td class="tamanhoValor">
                                <span  v-show="!nota.edit">{{nota.aps1}}</span>
                                <input class="tamanhoEdicao" type="text" v-model="nota.aps1" v-show="nota.edit" max="3" @change="valorMaximo(nota,'aps1',3)">
                            </td>

                            <td  class="tamanhoValor">
                                <span v-show="!nota.edit">{{nota.av2}}</span>
                                <input class="tamanhoEdicao" type="text" v-model="nota.av2" v-show="nota.edit" max="8" @change="valorMaximo(nota,'av2',8)">
                            </td>

                            <td class="tamanhoValor">
                                <span  v-show="!nota.edit">{{nota.aps2}}</span>
                                <input class="tamanhoEdicao" type="text" v-model="nota.aps2" v-show="nota.edit" max="2" @change="valorMaximo(nota,'aps2',2)">
                            </td>

                            <td class="tamanhoValor">
                                <span  v-show="!nota.edit">{{nota.av3}}</span>
                                <input class="tamanhoEdicao" type="text" v-model="nota.av3" v-show="nota.edit"  max="10" @change="valorMaximo(nota,'av3',10)">
                            </td>


                            <td style="width:300px">
                                <button class="btn btn-primary btn-sm" v-show="!nota.edit" v-on:click="editarNota(nota)" >Editar</button>
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
         <script src="../plugins/bootbox/bootbox.min.js"></script>
        <script type="text/javascript" charset="utf8" src="../plugins/datatables/jquery.dataTables.js"></script>
        <script src="../plugins/apiGatewayService.js"></script>
        <script src="./index.js"></script>  
    </body>
</html>

