/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

// A $( document ).ready() block.
$(document).ready(function () {
    console.log("ready!");
    /*
     * Falta ajustar a validação do token
     */
    //validarToken("../login/");


});

var officeVM = new Vue({
    el: '#app',
    data: {
        matriculaBusca: "2020100",
        nomeAluno: "",
        disciplinaSelecionada: "",
        listaDisciplinas: {},
        listAlunos: {},
        cursoSelecionado: null,
        notas: null,
        listaCursos: {},
        verNotas: false
    },
    mounted() {
        //  $('#tbNotas').DataTable({});
        apiRequest('http://localhost:8080/WebService/webresources/disciplina', null, 'GET', {"Authorization": getCokie("token")}).then(
                sucesso => {
                    this.listaDisciplinas = sucesso;
                }, erro => {

        }
        )
        apiRequest('http://localhost:8080/WebService/webresources/aluno', null, 'GET', {"Authorization": getCokie("token")}).then(
                sucesso => {
                    sucesso.forEach(element => {
                        element.edit = false,
                        element.novoAluno = false
                    });
                    this.listAlunos = sucesso;
                    
                }, erro => {

        }
        )


    },
    computed: {

        /*people2: function () {
         var _people = [];
         for (var i = 0, notas; people = this.people[i]; i++) {
         people.edit = false;
         _people.push(people);
         }
         return _people;
         }*/
    },
    methods: {
        verNota: function (matricula) {
            console.log('estupoido', matricula)
            if (matricula) {
                this.matriculaBusca = matricula;
                this.buscarAluno();
                this.verNotas = true;
            } else {
                this.verNotas = false;
            }
        },
        novoAluno: function(){
            this._aluno = Object.assign({
                    nomeAluno: this.nomeAluno,
                    edit: true,
                    novoAluno: true

                });
                this.listAlunos.push(this._aluno);
        },
        editarNota: function (nota) {
            this._originalNota = Object.assign({}, nota);
            nota.edit = true;
        },
        cancelarEdicao: function (nota) {
            Object.assign(nota, this._originalNota);
            if (nota.novaNota) {
                this.notas.pop(nota);
            }
            nota.edit = false;
        },
        salvarEdicao: function (nota) {
            this._originalNota = Object.assign({}, nota);
            if (nota.novaNota) {
                if (nota.idDisciplina == null) {
                    bootbox.alert({
                        message: "Selecione um disciplina!",
                        size: 'medium',
                        locale: 'pt-br',
                        centerVertical: true,
                    });
                    return;
                }
            }
            nota.novaNota = false;
            //Todo requisicao a api que vai fazer o crud das notas.
            //Sendo passado o objeto nota que possuem todas as informações necessárias

            apiRequest('http://localhost:8080/WebService/webresources/avaliacao', nota, 'PUT', {"Authorization": getCokie("token")}).then(
                    sucesso => {
                        nota.idAvaliacao = sucesso.idAvaliacao;
                        nota.edit = false;
                        this.buscarAluno();
                    }, erro => {
            }
            )


        },
        buscarAluno: function () {
            //TODO
            //Ajax para carregar as notas do aluno que irá preencher o objeto notas
            //Necessario criar a rota da api;
            if (this.matriculaBusca !== 0) {
                data = {
                    idAluno: this.matriculaBusca
                }
                apiRequest('http://localhost:8080/WebService/webresources/avaliacao', data, 'GET', {"Authorization": getCokie("token")}).then(
                        sucesso => {
                            if (sucesso.length > 0) {
                                sucesso.forEach(element => {
                                    element.edit = false,
                                            element.novaNota = false
                                });
                                console.log(sucesso)
                                if (sucesso[0].idAvaliacao !== 0) {
                                    this.notas = sucesso;
                                } else {
                                    this.notas = [];
                                }

                                this.nomeAluno = sucesso[0].nomeAluno;
                            } else {
                                this.nomeAluno = "Não encontrado"
                                this.notas = {};
                            }



                        }, erro => {
                    this.nomeAluno = "Não encontrado"
                    this.notas = {};
                }
                )
            }
        },
        adicionarNota: function () {
            if (this.notas !== null) {
                this._nota = Object.assign({
                    materia: '',
                    nomeAluno: this.nomeAluno,
                    idAvaliacao: null,
                    av1: '0',
                    aps1: '0',
                    av2: '0',
                    aps2: '0',
                    av3: '0',
                    matricula: this.matriculaBusca,
                    idDisciplina: null,
                    edit: true,
                    novaNota: true

                });
                this.notas.push(this._nota);
            }

        },
        atualizarDisciplina: function (nota) {
            nota.idDisciplina = this.disciplinaSelecionada.id;
            nota.materia = this.disciplinaSelecionada.text;
            console.log(this.disciplinaSelecionada.id, this.disciplinaSelecionada.text)
        },
        valorMaximo(objeto, valor, limite) {

            if (objeto[valor] > limite) {
                objeto[valor] = 0;
            }
        }
    }
})

$('#logout').click(function () {
    console.log('logout')
    eraseCookie('token');
    window.location.href = "../login";
});
