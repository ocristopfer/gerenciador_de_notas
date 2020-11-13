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
import model.Usuario;

/**
 *
 * @author ocris
 */
public class UsuarioDao {
        private PreparedStatement pst;
        private ResultSet rs;
        private Connection con;
        private String sql;
        
        public List<Usuario> getUsuarios() throws ClassNotFoundException, SQLException{
            List<Usuario> usuarios = new ArrayList<>();
            Usuario usuario = null;
            sql = "SELECT * FROM usuario;";
            con = DbCon.openCon();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getInt("tipo"));
                usuarios.add(usuario);
            }
            return usuarios;                       
        }
}
