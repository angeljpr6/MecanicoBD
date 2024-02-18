package com.example.mecanicobd.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.mecanicobd.model.Connections.con;

public class Service {

    private String description;
    private float cost;
    private float hours;

    public Service(String description, float cost, float hours) {
        this.description = description;
        this.cost = cost;
        this.hours = hours;
    }

    public void insertService(){
        try {
            String sentenciaSql = "INSERT INTO servicios (descripcion, costo, tiempo) VALUES (?, ?, ?)";
            PreparedStatement sentencia = con.prepareStatement(sentenciaSql);


            sentencia.setString(1, this.description);
            sentencia.setFloat(2, this.cost);
            sentencia.setFloat(3, this.hours);

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar el servicio en la base de datos: " + e.getMessage());
        }
    }

    public static int idLastService(){
        int lastId=0;

        String sentenciaSql = "SELECT idServicios FROM servicios ORDER BY idServicios DESC LIMIT 1;" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            resultado.next();

            while (resultado!=null){

                lastId= resultado.getInt(1);


                resultado.next();

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return lastId;
    }

    public static void insertRepair(int idServices, String licensePlate, Date date){

        try {
            String sentenciaSql = "INSERT INTO reparacion (idServicio, matricula, fecha, estado) VALUES(?, ?, ?, ?);";
            PreparedStatement sentencia = con.prepareStatement(sentenciaSql);


            sentencia.setInt(1, idServices);
            sentencia.setString(2, licensePlate);
            sentencia.setDate(3, date);
            sentencia.setString(4, "En proceso");

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar la reparacion en la base de datos: " + e.getMessage());
        }

    }
}
