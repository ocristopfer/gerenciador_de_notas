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
import model.Curso;

/**
 *
 * @author ocris
 */
public class CursoDao {
     private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public List<Curso> getCursos() throws ClassNotFoundException, SQLException {
        List<Curso> cursos = new ArrayList<>();
        Curso curso = null;
        sql = "SELECT * FROM curso;";
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            curso = new Curso();
            curso.setIdCurso(rs.getInt("idCURSO"));
            curso.setNomeCurso(rs.getString("NOME_CURSO"));
            cursos.add(curso);
        }
        DbCon.closeCon();
        return cursos;
    }
}
