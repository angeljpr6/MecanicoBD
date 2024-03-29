package com.example.mecanicobd;

import com.example.mecanicobd.model.Connections;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Connections con = new Connections();
        con.connect();
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("home.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}