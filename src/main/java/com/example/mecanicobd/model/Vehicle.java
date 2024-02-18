package com.example.mecanicobd.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.mecanicobd.model.Connections.con;

public class Vehicle {

    private String licensePlate;

    public Vehicle(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static ArrayList<Vehicle> allVehicles(){
        ArrayList<Vehicle> vehicles = new ArrayList<>();

        String sentenciaSql = "SELECT matricula from vehiculos" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            resultado.next();

            while (resultado!=null){

                String licensePlate = resultado.getString(1);

                vehicles.add(new Vehicle(licensePlate));

                resultado.next();

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return vehicles;
    }

    public static boolean existVehicle(String licensePlate){
        String sentenciaSql = "SELECT matricula from vehiculos where matricula = ?" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);

            sentencia.setString(1,licensePlate);

            resultado = sentencia.executeQuery();

            if (resultado.next()){
                return true;
            }else return false;

        } catch (SQLException e) {
            System.out.println("Error");
        }
        return false;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
