function apiRequest(url, data, type = "POST", headers = "") {
        if (type != "GET") {
            data = JSON.stringify(data);
        }

        return new Promise((resolve, reject) => {
            $.ajax({
                type: type,
                crossDomain: true,
                dataType: "json",
                contentType: "application/json; charset=utf-8",
                url: url,
                data: data,
                headers: headers,
                success: function (data) {
                    resolve(data);
                },
                error: function (data) {
                    reject(data);
                },
            });
        });
    }
 

function validarToken(urlRedirect = "") {
    token = getCokie('token')
    if (token == ""){
        window.location.href = urlRedirect;
    }

    url = "http://localhost:8080/WebService/webresources/usuario/validar";
    apiRequest(url, token).then(
            sucesso => {
                return true;
            }
    , erro => {
        console.log('apagar')
        eraseCookie('token');
        window.location.href = urlRedirect;
    })
    
}

function getCokie(cname) {
        var name = cname + "=";
        var ca = document.cookie.split(';');
        for (var i = 0; i < ca.length; i++) {
            var c = ca[i];
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) == 0) {
                return c.substring(name.length, c.length);
            }
        }
        return "";
    }
    
function setCookie(cname, cvalue, exdays) {
        var d = new Date();
        d.setTime(d.getTime() + (exdays*24*60*60*1000));
        var expires = "expires="+ d.toUTCString();
        document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
    }
  
function eraseCookie(name) {
    document.cookie = name + '=; Max-Age=0';
}