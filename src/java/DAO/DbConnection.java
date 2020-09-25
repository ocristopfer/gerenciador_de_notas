package DAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ocris
 */
public class DbConnection {

    public Connection getConnection() throws SQLException {
        try {
            String jdbcURL = "jdbc:mysql://localhost:3306/gerenciador_de_notas";
            String dbUser = "root";
            String dbPassword = "suporte";

            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            // return DriverManager.getConnection("jdbc:mysql://localhost/gerenciador_de_notas?" +
            //"user=dbUser&password=dbPassword&useSSL=false&allowPublicKeyRetrieval=true");

            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "suporte");
            properties.put("useSSL", "false");
            properties.put("allowPublicKeyRetrieval", "true");
            return DriverManager.getConnection(jdbcURL, properties);
        } catch (Exception ex) {
            throw new SQLException();
            //return null;
        }
    }
}
