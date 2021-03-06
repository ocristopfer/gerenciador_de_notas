/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.CursoDao;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import model.Curso;

import model.Token;

/**
 * REST Web Service
 *
 * @author ocris
 */
@Path("curso")
public class CursoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CursoWS
     */
    public CursoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.CursoWS
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@HeaderParam("Authorization") String token) throws ClassNotFoundException, SQLException {
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
        if (token.isEmpty()) {
            throw new WebApplicationException(400);
        }

        Token otoken = new Token();
        otoken.setToken(token);
        if (otoken.validarToken(otoken).equals("professor")) {

            CursoDao cursoDao = new CursoDao();
            List<Curso> cursos = cursoDao.getCursos();
            Gson g = new Gson();
            return g.toJson(cursos);
        } else {
            throw new WebApplicationException(400);
        }
    }

    /**
     * PUT method for updating or creating an instance of CursoWS
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
