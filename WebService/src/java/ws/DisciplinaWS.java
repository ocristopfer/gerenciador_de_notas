/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.DisciplinaDao;
import java.sql.SQLException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import model.Disciplina;
import model.Token;

/**
 * REST Web Service
 *
 * @author ocris
 */
@Path("disciplina")
public class DisciplinaWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DisciplinaWS
     */
    public DisciplinaWS() {
    }

    /**
     * Retrieves representation of an instance of ws.DisciplinaWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@HeaderParam("Authorization") String token) throws ClassNotFoundException, SQLException {
        if (token.isEmpty()){
             throw new WebApplicationException(400);
        }
        
        Token otoken = new Token();
        otoken.setToken(token);
        if (otoken.validarToken(otoken).equals("professor")){
            
            DisciplinaDao disciplinaDao = new DisciplinaDao();
            List<Disciplina> disciplinas = disciplinaDao.getDisciplinas();
            Gson g = new Gson();
            return g.toJson(disciplinas);
        }else{
             throw new WebApplicationException(400);
        }        

        //TODO return proper representation object
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of DisciplinaWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
