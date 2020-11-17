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
    validarToken("../login/");

    apiRequest('http://localhost:8080/WebService/webresources/aluno/notas', null, 'GET', {"Authorization": getCokie("token")}).then(
            sucesso => {
                $("#nomeAluno").text(sucesso[0].nomeAluno)
                $('#dtAluno').DataTable({
                    dom: 'rt',
                    data: sucesso,
                    columns: [
                        {data: 'materia'},
                        {data: 'notaAv1'},
                        {data: 'notaAps1'},
                        {data: 'notaAv2'},
                        {data: 'notaAps2'},
                        {data: 'notaAv3'},
                        {data: 'media'},
                        {data: 'resultado'}
                    ]
                });
            }, erro => {

    }
    )

});

$('#logout').click(function () {
    console.log('logout')
         eraseCookie('token');
            window.location.href = "../login";
});

