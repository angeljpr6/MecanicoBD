package com.example.mecanicobd.view;

import com.example.mecanicobd.model.Client;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Home implements Initializable {

    @FXML
    private TableColumn<Client, Integer> idColumn = new TableColumn<>("ID");
    @FXML
    private TableColumn<Client, String> nombreColumn = new TableColumn<>("nombre");
    @FXML
    private TableColumn<Client, String> calleColumn = new TableColumn<>("calle");
    @FXML
    private TableColumn<Client, Integer> numCalleColumn = new TableColumn<>("numero");
    @FXML
    private TableColumn<Client, String> ciudadColumn = new TableColumn<>("ciudad");
    @FXML
    private TableColumn<Client, String> codPostalColumn = new TableColumn<>("CP");
    @FXML
    private TableColumn<Client, String> telfColumn = new TableColumn<>("TLF");
    public TableView tablaClientes;

    public TextField nombreEditado;
    public TextField calleEditada;
    public TextField numCalleEditado;
    public TextField ciudadEditada;
    public TextField codPostEditado;
    public TextField telfEditado;
    public Pane editPane;

    private Client client = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //configureColumnProperties();
        //initializeTableColumns();
        uploadClientTable();
    }

    private void initializeTableColumns() {
        tablaClientes.getColumns().clear();
        tablaClientes.getColumns().add(idColumn);
        tablaClientes.getColumns().add(nombreColumn);
        tablaClientes.getColumns().add(calleColumn);
        tablaClientes.getColumns().add(numCalleColumn);
        tablaClientes.getColumns().add(ciudadColumn);
        tablaClientes.getColumns().add(codPostalColumn);
        tablaClientes.getColumns().add(telfColumn);
        //tablaClientes.getColumns().addAll(idColumn, nombreColumn, calleColumn, numCalleColumn, ciudadColumn, codPostalColumn, telfColumn);
    }
    private void configureColumnProperties() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
        calleColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("street"));
        numCalleColumn.setCellValueFactory(new PropertyValueFactory<Client,Integer>("num"));
        ciudadColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("city"));
        codPostalColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("cp"));
        telfColumn.setCellValueFactory(new PropertyValueFactory<Client,String>("phoneNumber"));
    }

    public void uploadClientTable(){
        tablaClientes.getColumns().clear();

        ObservableList<Client> clientsList = FXCollections.observableArrayList();
        //ArrayList<Client> clients = Client.allClient();
        ArrayList<Client> clients = new ArrayList<>();
        clients.add(new Client(1,"lolo","Calle",2,"aa","26363","2323232323ddd"));

        clientsList.addAll(clients);

        tablaClientes.setItems(clientsList);


    }

    public void editData(ActionEvent actionEvent) {


    }

    public void deleteClient(ActionEvent actionEvent) {
        client.deleteClient();
    }

    public void openEditPane(SortEvent<TableView> tableViewSortEvent) {
        editPane.setVisible(true);
        client = (Client) tablaClientes.getSelectionModel().getSelectedItem();
    }
}
