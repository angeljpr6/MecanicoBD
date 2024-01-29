package com.example.mecanicobd;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connection {

    final String HOST = "";
    final int PORT = 0;
    DataInputStream in;
    DataOutputStream out;

    public void connect(){

        try {
            Socket sc = new Socket(HOST,PORT);


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
