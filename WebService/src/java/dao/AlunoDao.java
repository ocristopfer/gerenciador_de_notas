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
import model.Nota;
import model.Usuario;

/**
 *
 * @author ocris
 */
public class AlunoDao {

    private PreparedStatement pst;
    private ResultSet rs;
    private Connection con;
    private String sql;

    public  List<Nota> getNotasAluno(String matricula) throws ClassNotFoundException, SQLException {
        sql = "SELECT * FROM usuario WHERE login = '" + matricula + "'";
        List<Nota> notas = new ArrayList<>();
        con = DbCon.openCon();
        pst = con.prepareStatement(sql);
        rs = pst.executeQuery();
        while (rs.next()) {
            Nota anota = new Nota();
            anota.setMateria(rs.getString("materia"));
            anota.setNotaAv1(rs.getFloat("notaAv1"));
            anota.setNotaAv2(rs.getFloat("notaAv2"));
            anota.setNotaAv3(rs.getFloat("notaAv3"));
            notas.add(anota);
        }
        return notas;
    }

}
