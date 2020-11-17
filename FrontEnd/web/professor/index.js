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
    el: '#listaNotas',
    data: {
        matriculaBusca: "2020100",
        nomeAluno: "",
        notas: {}
    },
    mounted() {
        //  $('#tbNotas').DataTable({});

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
        editarNota: function (nota) {
            this._originalNota = Object.assign({}, nota);
            nota.edit = true;
        }, cancelarEdicao: function (nota) {
            Object.assign(nota, this._originalNota);
            if (nota.novaNota) {
                this.notas.pop(nota);
            }
            nota.edit = false;
        }, salvarEdicao: function (nota) {
            this._originalNota = Object.assign({}, nota);
            nota.novaNota = false;
            //Todo requisicao a api que vai fazer o crud das notas.
            //Sendo passado o objeto nota que possuem todas as informações necessárias

            apiRequest('http://localhost:8080/WebService/webresources/avaliacao', nota, 'PUT', {"Authorization": getCokie("token")}).then(
                    sucesso => {
                        nota.idAvaliacao = sucesso.idAvaliacao;
                        nota.edit = false;
                    }, erro => {
            }
            )


        }, buscarAluno: function () {
            //TODO
            //Ajax para carregar as notas do aluno que irá preencher o objeto notas
            //Necessario criar a rota da api;
            if (this.matriculaBusca != 0) {
                data = {
                    idAluno: this.matriculaBusca
                }
                apiRequest('http://localhost:8080/WebService/webresources/avaliacao', data, 'GET', {"Authorization": getCokie("token")}).then(
                        sucesso => {
                            sucesso.forEach(element => {
                                element.edit = false,
                                        element.novaNota = false
                            });
                            this.notas = sucesso;
                            this.nomeAluno = sucesso[0].nomeAluno;

                        }, erro => {

                }
                )
            }
        }, adicionarNota: function () {
            if (this.notas.length > 0) {
                this._nota = Object.assign({
                    materia: 'Nova NOta',
                    idAvaliacao: null,
                    av1: '5',
                    aps1: '2',
                    av2: '5',
                    aps2: '2',
                    av3: '5',
                    edit: true,
                    novaNota: true

                });
                this.notas.push(this._nota);
            }

        }
    }
})

$('#logout').click(function () {
    console.log('logout')
         eraseCookie('token');
            window.location.href = "../login";
});
