package com.example.mecanicobd.model;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.mecanicobd.model.Connections.con;

public class Mechanic {

    private int id;
    private String name;

    public Mechanic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ArrayList<Mechanic> allMechanics(){
        ArrayList<Mechanic> mechanics = new ArrayList<>();

        String sentenciaSql = "SELECT idmecanico, (empleados).persona.nombre from mecanico" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            resultado.next();

            while (resultado!=null){

                int id = resultado.getInt(1);
                String name = resultado.getString(2);

                mechanics.add(new Mechanic(id,name));

                resultado.next();

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return mechanics;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
