/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    validarToken("../login/");
});

var officeVM = new Vue({
    el: '#app',
    data: {
        matriculaBusca: "",
        nomeAluno: "",
        listAlunos: {},
        listaDisciplinas: {},
        disciplinaSelecionada: "",
        listaCursos: {},
        cursoSelecionado: null,
        notas: null,
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
        apiRequest('http://localhost:8080/WebService/webresources/curso', null, 'GET', {"Authorization": getCokie("token")}).then(
                sucesso => {
                    this.listaCursos = sucesso;
                }, erro => {

        }
        )
        this.listarAlunos();


    },
    computed: {

    },
    methods: {
        novoAluno: function () {
            this._aluno = Object.assign({
                nome: this.nomeAluno,
                idCurso: null,
                edit: true,
                novoAluno: true

            });
            this.listAlunos.push(this._aluno);
        },
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
        editarAluno: function (aluno) {
            this._originalAluno = Object.assign({}, aluno);
            aluno.edit = true;
        },
        cancelarEdicaoAluno: function (aluno) {
            Object.assign(aluno, this._originalAluno);
            if (aluno.novoAluno) {
                this.listAlunos.pop(aluno);
            }
            this._originalAluno = null;
            aluno.edit = false;
        },
        excluirAluno: function (aluno) {
            this._aluno = aluno;
            bootbox.confirm({
                message: "Só é possivel excluir o aluno, se todas as notas dele já foram previamente excluidas, tem certeza que deseja proseguir com a exclusão ?",
                size: 'medium',
                locale: 'pt-br',
                centerVertical: true,
                buttons: {
                    confirm: {
                        label: 'Sim',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'Não',
                        className: 'btn-danger'
                    }
                },
                callback: this.callBackexcluirAluno
            });


        },
        callBackexcluirAluno(result) {
            if (result) {
                apiRequest('http://localhost:8080/WebService/webresources/aluno/delete', this._aluno, 'PUT', {"Authorization": getCokie("token")}).then(
                        sucesso => {
                            console.log('excluir')
                            this._aluno = null;
                            this.listarAlunos();
                        }, erro => {
                }
                )
            }
        }
        ,
        atualizarCurso: function (aluno) {
            aluno.idCurso = this.cursoSelecionado.id;
        },
        salvarAluno: function (aluno) {
            aluno.novoAluno = false;
            //Todo requisicao a api que vai fazer o crud das notas.
            //Sendo passado o objeto nota que possuem todas as informações necessárias

            apiRequest('http://localhost:8080/WebService/webresources/aluno', aluno, 'PUT', {"Authorization": getCokie("token")}).then(
                    sucesso => {
                        this.listarAlunos();
                    }, erro => {
            }
            )
        },
        listarAlunos: function () {
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
        editarNota: function (nota) {
            this._originalNota = Object.assign({}, nota);
            nota.edit = true;
        },
        cancelarEdicao: function (nota) {
            Object.assign(nota, this._originalNota);
            if (nota.novaNota) {
                this.notas.pop(nota);
            }
            this._originalNota = null;
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

        }, exlcuirNota: function (nota) {
            this._nota = nota;
            bootbox.confirm({
                message: "Tem certeza que deseja excluir essa avaliação ?",
                size: 'medium',
                locale: 'pt-br',
                centerVertical: true,
                buttons: {
                    confirm: {
                        label: 'Sim',
                        className: 'btn-success'
                    },
                    cancel: {
                        label: 'Não',
                        className: 'btn-danger'
                    }
                },
                callback: this.callBackExcluirNota,
            });

        }, callBackExcluirNota: function (result) {
            if (result) {
                callback = this;
                apiRequest('http://localhost:8080/WebService/webresources/avaliacao/delete', this._nota, 'PUT', {"Authorization": getCokie("token")}).then(
                        sucesso => {
                            this._nota = null;
                            this.buscarAluno();
                        }, erro => {
                }
                )
            }
        }
        ,
        atualizarDisciplina: function (nota) {
            nota.idDisciplina = this.disciplinaSelecionada.id;
            nota.materia = this.disciplinaSelecionada.text;
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
    setCookie('token', '');
    eraseCookie('token');
    window.location.href = "http://localhost:8080/FrontEnd/login/";
});
