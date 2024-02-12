package com.example.mecanicobd.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.mecanicobd.model.Connections.con;

public class Client {

    private int id ;
    private String name;
    private String street;
    private int num;
    private String city;
    private String cp;
    private String phoneNumber;

    public Client(int id, String name, String street, int num, String city, String cp, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.num = num;
        this.city = city;
        this.cp = cp;
        this.phoneNumber = phoneNumber;
    }

    public static ArrayList<Client> allClient(){
        ArrayList<Client> clients = new ArrayList<>();

        String sentenciaSql = "SELECT * from clientes" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            resultado.next();

            while (resultado!=null){

                int id = resultado.getInt("id");
                String name = resultado.getString("nombre");
                String street = resultado.getString("nombre");;
                int num;
                String city;
                String cp;
                String phoneNumber;

            }


        } catch (SQLException e) {
            System.out.println("Error");
        }

        return clients;
    }
}
