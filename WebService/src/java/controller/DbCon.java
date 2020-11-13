/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author ocris
 */
public class DbCon {
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciador_notas";
    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String SENHA = "suporte";
    private static Connection CON;
    
    public static Connection openCon() throws ClassNotFoundException, SQLException{
        try {
            Class.forName(DRIVER).newInstance();
            Properties properties = new Properties();
            properties.put("user", USER);
            properties.put("password", SENHA);
            properties.put("useSSL", "false");
            properties.put("allowPublicKeyRetrieval", "true");
            CON = DriverManager.getConnection(URL, properties); 
            return CON;
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DbCon.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static void closeCon() throws SQLException
    {
        CON.close();
    }
    
}
