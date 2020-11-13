/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ocris
 */
public class Usuario {

    public int getIdusuarios() {
        return idusuarios;
    }

    public void setIdusuarios(int idusuarios) {
        this.idusuarios = idusuarios;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    private int idusuarios;
    private String nome;
    private String login;
    private String senha;
    private String tipo;

    public Usuario getUsuario(String login, String senha) {
        DbConnection DbCon = new DbConnection();
        Usuario usuario = null;
        
        try (Connection connection = DbCon.getConnection()) {

            String sql = "SELECT * FROM usuarios WHERE login = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, login);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                if (login.equals(result.getString("login")) && senha.equals(result.getString("senha"))) {
                    usuario = new Usuario();
                    usuario.setIdusuarios(result.getInt("idusuarios"));
                    usuario.setNome(result.getString("nome"));
                    usuario.setLogin(result.getString("login"));
                    usuario.setSenha(result.getString("senha"));
                    usuario.setTipo(result.getString("tipo"));
                }

            }
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return usuario;
    }
}
