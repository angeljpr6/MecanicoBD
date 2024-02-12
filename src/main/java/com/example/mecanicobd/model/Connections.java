package com.example.mecanicobd.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connections {

    String url = "jdbc:postgresql://localhost:5433/taller" ;
    String user = "postgres"; // Establecer el que corresponda, este es el root
    String password="1234";
    public static Connection con;

    public java.sql.Connection connect() {
        con = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            if (con != null) {

            }


        } catch (SQLException ex) {
            Logger.getLogger(Connections.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }


}
