package com.example.mecanicobd.view;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Home implements Initializable {
    public TableColumn idColumn;
    public TableColumn nombreColumn;
    public TableColumn calleColumn;
    public TableColumn numCalleColumn;
    public TableColumn ciudadColumn;
    public TableColumn codPostalColumn;
    public TableColumn telfColumn;
    public TableView tablaClientes;

    public TextField nombreEditado;
    public TextField calleEditada;
    public TextField numCalleEditado;
    public TextField ciudadEditada;
    public TextField codPostEditado;
    public TextField telfEditado;
    public Pane editPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void uploadClientTable(){

    }

    public void editData(ActionEvent actionEvent) {


    }

    public void deleteClient(ActionEvent actionEvent) {

    }

}
