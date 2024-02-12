package com.example.mecanicobd.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import static com.example.mecanicobd.model.Connections.con;

public class Admin {

    private int idAdmin;
    private int age;
    private Date birthdate;
    private String phoneNumber;

    public Admin(int idAdmin, int age, Date birthdate, String phoneNumber) {
        this.idAdmin = idAdmin;
        this.age = age;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
    }

    public static Admin logInAdmin(String phoneNumber, int idAdmin){
        Admin admin = null;
        String sentenciaSql = "SELECT * from administradores where idadmin = ? and tlfcorporativo = ?" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setInt( 1, idAdmin);
            sentencia.setString( 2, phoneNumber);
            resultado = sentencia.executeQuery();

            resultado.next();

            Date birthdate = resultado.getDate( "fechanac");
            int age = resultado.getInt("edad");
            admin = new Admin(idAdmin,age,birthdate,phoneNumber);

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return admin;

    }
}
