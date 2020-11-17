/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dao.AlunoDao;
import dao.AvaliacaoDao;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import model.Avaliacao;
import model.Nota;
import model.Token;

/**
 * REST Web Service
 *
 * @author ocris
 */
@Path("avaliacao")
public class AvaliacaoWS {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AvaliacaoWS
     */
    public AvaliacaoWS() {
    }

    /**
     * Retrieves representation of an instance of ws.AvaliacaoWS
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@HeaderParam("Authorization") String token, @QueryParam("idAluno") int idAluno) throws ClassNotFoundException, SQLException {
        if (token.isEmpty()){
             throw new WebApplicationException(400);
        }
        
        Token otoken = new Token();
        otoken.setToken(token);
        if (otoken.validarToken(otoken).equals("professor") && idAluno != 0){
            
            AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
            List<Avaliacao> avaliacaes = avaliacaoDao.getAvalicaoAluno(idAluno);
            Gson g = new Gson();
            return g.toJson(avaliacaes);
        }else{
             throw new WebApplicationException(400);
        }
        //TODO return proper representation object
        //throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of AvaliacaoWS
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public String putJson(@HeaderParam("Authorization") String token, String content) throws ClassNotFoundException, SQLException {
                if (token.isEmpty()){
             throw new WebApplicationException(400);
        }
        
        Token otoken = new Token();
        otoken.setToken(token);
        if (otoken.validarToken(otoken).equals("professor")){
            Gson g = new Gson();
            Avaliacao request = g.fromJson(content, Avaliacao.class);
            AvaliacaoDao avaliacaoDao = new AvaliacaoDao();
            if(request.getIdAvaliacao() == null){
                return g.toJson(avaliacaoDao.salvar(request));
            }else{
                return g.toJson(avaliacaoDao.atualizar(request));
            }
        }else{
             throw new WebApplicationException(400);
        }
    }
    
}
