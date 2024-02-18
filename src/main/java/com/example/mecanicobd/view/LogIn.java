package com.example.mecanicobd.view;


import com.example.mecanicobd.model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LogIn {


    public TextField phoneField;
    public TextField idField;
    public Label dataError;

    public void logIn(ActionEvent actionEvent) throws IOException {
        verifyData();
    }

    public void verifyData() throws IOException {
        if(idField.getText().matches("\\d+")) {
            Admin admin = Admin.logInAdmin(phoneField.getText(), Integer.parseInt(idField.getText()));
            if (admin != null) {
                changeWindow();
            } else dataError.setVisible(true);
        } else dataError.setVisible(true);
    }

    public void changeWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/mecanicobd/home.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage homeStage = new Stage();
        homeStage.setTitle("Home");
        homeStage.setResizable(false);
        homeStage.setScene(scene);
        homeStage.show();

        Stage myStage = (Stage) this.idField.getScene().getWindow();
        myStage.close();
    }
}