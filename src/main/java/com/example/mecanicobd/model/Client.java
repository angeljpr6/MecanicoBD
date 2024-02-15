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

        String sentenciaSql = "SELECT idcliente, (persona).nombre, (persona).direccion.calle, (persona).direccion.num," +
                "(persona).direccion.ciudad, (persona).direccion.cp, tlf from clientes" ;
        PreparedStatement sentencia = null;
        ResultSet resultado = null;
        try {
            sentencia = con.prepareStatement(sentenciaSql);
            resultado = sentencia.executeQuery();

            resultado.next();

            while (resultado!=null){

                int id = resultado.getInt(1);
                String name = resultado.getString(2);
                String street = resultado.getString(3);
                int num = resultado.getInt(4);
                String city = resultado.getString(5);
                String cp = resultado.getString(6);
                String phoneNumber = resultado.getString(7);

                clients.add(new Client(id,name,street,num,city,cp,phoneNumber));

                resultado.next();

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return clients;
    }

    public void deleteClient(){
        String sentenciaSql = "delete from clientes where idcliente = ?" ;
        PreparedStatement sentencia = null;

        try {

            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setInt( 1, this.id);
            sentencia.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateClient(){

    }
}
