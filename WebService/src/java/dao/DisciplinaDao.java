/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import controller.DbCon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Disciplina;


/**
 *
 * @author ocris
 */
public class DisciplinaDao {
     private PreparedStatement pst;
        private ResultSet rs;
        private Connection con;
        private String sql;
        
        public List<Disciplina> getDisciplinas() throws ClassNotFoundException, SQLException{
            List<Disciplina> disciplinas = new ArrayList<>();
            Disciplina disciplina = null;
            sql = "SELECT * FROM disciplina;";
            con = DbCon.openCon();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                disciplina = new Disciplina();
                disciplina.setIdDisciplina(rs.getInt("idDISCIPLINA"));
                disciplina.setNomeDisciplina(rs.getString("DISCIPLINA_NOME"));
                disciplinas.add(disciplina);
            }
            DbCon.closeCon();
            return disciplinas;                       
        }
}
