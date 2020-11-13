/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.UsuarioDao;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import model.Token;
import model.Usuario;

/**
 * REST Web Service
 *
 * @author ocris
 */
@Path("usuario")
public class UsuarioWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    /**
     * Retrieves representation of an instance of ws.UsuarioWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        return "Ola meu Web Service";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public String getUsuarios() throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        UsuarioDao userDao = new UsuarioDao();
        List<Usuario> usuarios = userDao.getUsuarios();
        return g.toJson(usuarios);
    }

    /**
     * PUT method for updating or creating an instance of UsuarioWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("autenticar")
    public String autenticarUsuario(String content) throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        Usuario requisicao = g.fromJson(content, Usuario.class);
        UsuarioDao userDao = new UsuarioDao();
        Usuario user = userDao.getUsuarioByLogin(requisicao.getLogin());
        if (user != null) {
            if (user.getSenha().equals(requisicao.getSenha())) {
                String token = Base64.getEncoder().encodeToString(("token;" + user.getTipo() + ";" + user.getId()).getBytes());
                return g.toJson(token);
            }
        }
        throw new WebApplicationException(400);

    }
    
        @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("validar")
    public String validarUsuario(String content) throws ClassNotFoundException, SQLException {
        Gson g = new Gson();
        Token token = g.fromJson(content, Token.class);
        String tokenValidado = token.validarToken(token);
        if(tokenValidado != null){
            return g.toJson(tokenValidado);
        }
  
        throw new WebApplicationException(400);

    }
}
