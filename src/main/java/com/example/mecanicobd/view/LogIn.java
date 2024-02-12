package com.example.mecanicobd.view;


import com.example.mecanicobd.model.Admin;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LogIn {


    public TextField phoneField;
    public TextField idField;
    public Label dataError;

    public void logIn(ActionEvent actionEvent) {
        verifyData();
    }

    public void verifyData(){
        if(idField.getText().matches("\\d+")) {
            Admin admin = Admin.logInAdmin(phoneField.getText(), Integer.parseInt(idField.getText()));
            if (admin != null) {
                System.out.println("Todo okey");
            } else dataError.setVisible(true);
        } else dataError.setVisible(true);
    }
}