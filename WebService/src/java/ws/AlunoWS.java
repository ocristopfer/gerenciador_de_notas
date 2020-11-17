/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AlunoDao;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import model.Nota;
import model.Token;

/**
 * REST Web Service
 *
 * @author ocris
 */
@Path("aluno")
public class AlunoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AlunoWS
     */
    public AlunoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.AlunoWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AlunoWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("notas")
    public String getNotasAluno(@HeaderParam("Authorization") String token) throws ClassNotFoundException, SQLException {
        if (token.isEmpty()){
             throw new WebApplicationException(400);
        }
        Token otoken = new Token();
        otoken.setToken(token);
        if (otoken.validarToken(otoken).equals("aluno")){
            String matricula = Token.descriptografarToken(token).split(";")[2];
            AlunoDao oAluno = new AlunoDao();
            List<Nota> notas = oAluno.getNotasAluno(matricula);
            
            Gson g = new Gson();
            return g.toJson(notas);
        }else{
             throw new WebApplicationException(400);
        }

    }
}
