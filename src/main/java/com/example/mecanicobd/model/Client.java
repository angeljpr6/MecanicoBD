package com.example.mecanicobd.model;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import static com.example.mecanicobd.model.Connections.con;

public class Client {

    private int id ;
    private String name;
    private String street;
    private int num;
    private String city;
    private String cp;
    private String[] phoneNumber;

    public Client(int id, String name, String street, int num, String city, String cp, String[] phoneNumber) {
        this.id = id;
        this.name = name;
        this.street = street;
        this.num = num;
        this.city = city;
        this.cp = cp;
        this.phoneNumber = phoneNumber;
    }

    public Client(String name, String street, int num, String city, String cp, String[] phoneNumber) {
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

                Object[] phoneNumber = (Object[]) resultado.getArray( 7).getArray();
                String[] phoneNumberArray = Arrays.asList(phoneNumber).toArray(new String[phoneNumber.length]);


                clients.add(new Client(id,name,street,num,city,cp,phoneNumberArray));

                resultado.next();

            }

        } catch (SQLException e) {
            System.out.println("Error");
        }

        return clients;
    }

    public void deleteClient(){
        String sentenciaSql = "delete from clientes where idcliente = ?";
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
        try {
            String sentenciaSql = "UPDATE clientes SET persona.nombre = ?, persona.direccion.calle = ?, persona.direccion.num = ?, persona.direccion.ciudad = ?, persona.direccion.cp = ?, tlf = ? WHERE idcliente = ?";
            PreparedStatement sentencia = con.prepareStatement(sentenciaSql);


            sentencia.setString(1, this.name);
            sentencia.setString(2, this.street);
            sentencia.setInt(3, this.num);
            sentencia.setString(4, this.city);
            sentencia.setString(5, this.cp);
            sentencia.setArray(6, con.createArrayOf("VARCHAR", this.phoneNumber));

            sentencia.setInt(7, this.id);

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al actualizar el cliente en la base de datos: " + e.getMessage());
        }
    }

    public boolean insertClient(){
        try {
            String sentenciaSql = "INSERT INTO clientes(persona.nombre, persona.direccion.calle, persona.direccion.num, persona.direccion.ciudad, persona.direccion.cp, tlf) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement sentencia = con.prepareStatement(sentenciaSql);


            sentencia.setString(1, this.name);
            sentencia.setString(2, this.street);
            sentencia.setInt(3, this.num);
            sentencia.setString(4, this.city);
            sentencia.setString(5, this.cp);
            sentencia.setArray(6, con.createArrayOf("VARCHAR", this.phoneNumber));

            sentencia.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al insertar el cliente en la base de datos: " + e.getMessage());
            return false;
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStreet() {
        return street;
    }

    public int getNum() {
        return num;
    }

    public String getCity() {
        return city;
    }

    public String getCp() {
        return cp;
    }

    public String getPhoneNumber() {
        StringBuilder phoneNumberString = new StringBuilder();

        for (int i = 0; i < phoneNumber.length; i++) {
            phoneNumberString.append(phoneNumber[i]).append(" ");
        }

        return phoneNumberString.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setPhoneNumber(String[] phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
