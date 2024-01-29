package com.example.mecanicobd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    public String url = "jdbc:postgresql://localhost:5432/taller";
    public String usuario = "postgres";
    public String password = "1234";
    public static Connection con;

    public Connection conectar() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(url, usuario, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

}
