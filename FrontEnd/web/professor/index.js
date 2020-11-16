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
        matriculaBusca: "",
        notas: [
            {
                materia: 'Materia1',
                id:"1",
                av1: '5',
                aps1: '2',
                av2: '5',
                aps2: '2',
                av3: '5',
                edit: false
            },
            {
                materia: 'Materia1',
                id:"1",
                av1: '5',
                aps1: '2',
                av2: '5',
                aps2: '2',
                av3: '5',
                edit: false
            },
            {
                materia: 'Materia1',
                id:"1",
                av1: '5',
                aps1: '2',
                av2: '5',
                aps2: '2',
                av3: '5',
                edit: false
            }
        ]
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
            nota.edit = false;
        }, salvarEdicao: function (nota) {
            this._originalNota = Object.assign({}, nota);
            
            //Todo requisicao a api que vai fazer o crud das notas.
            //Sendo passado o objeto nota que possuem todas as informações necessárias
            nota.edit = false;
        },buscarAluno : function(aluno){
            //TODO
            //Ajax para carregar as notas do aluno que irá preencher o objeto notas
            //Necessario criar a rota da api;
            if(this.matriculaBusca != 0){
                console.log("buscar")
            }
        }
    }
})

