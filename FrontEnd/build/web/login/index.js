/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(".form-signin").submit(function (event) {
    //alert("Handler for .submit() called.");
    event.preventDefault();
});

//var script = document.createElement('script');;
//script.src = '../plugins/apiGatewayService.js';
//document.getElementsByTagName('head')[0].appendChild(script);


$('#login').click(function () {
    url = "http://localhost:8080/WebService/webresources/usuario/autenticar";
    data = {
        login: $('#usuario').val(),
        senha: $('#senha').val(),
    }

    apiRequest(url, data).then(
            sucesso => {
                console.log('certo', sucesso);
                setCookie("token", sucesso);

                validarToken().then(
                        sucesso => {
                            window.location.href = "../" + sucesso + "/";
                        }
                )

            }
    , erro => {
        alert('Erro ao logar');
        console.log(erro);
    })
})

