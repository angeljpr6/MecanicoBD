package com.example.mecanicobd.view;

import com.example.mecanicobd.model.Client;
import com.example.mecanicobd.model.Mechanic;
import com.example.mecanicobd.model.Service;
import com.example.mecanicobd.model.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class Home implements Initializable {

    public Pane newClientPane;
    public TextField nombreNuevo;
    public TextField calleNueva;
    public TextField numCalleNueva;
    public TextField ciudadNueva;
    public TextField codPostNuevo;
    public TextField telfNuevo;
    public Label errorDataNewClient;
    public Label addedClient;
    public Label repareLabel;
    public Pane repairsPane;
    public Label clientLabel1;
    public TableView tableMechanic;
    public TableColumn<Mechanic, Integer> idMechanicColumn;
    public TableColumn<Mechanic, String> nameMechaniColumn;

    public TableView tableLicensePlate;
    public TableColumn<Vehicle, String> licensePlateColumn;
    public TextField licenPlateServ;
    public TextField serviceDescrip;
    public TextField serviceCost;
    public TextField serviceHour;
    public TextField repairDate;
    public Label serviceCreated;
    public Label serviceError;

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
    @FXML
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
        configureClientsColumnProperties();
        uploadClientTable();

        configureMechanicsColumnProperties();
        uploadMechanicTable();

        configureVehiclesColumnProperties();
        uploadVehiclesTable();
    }

    private void configureClientsColumnProperties() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        calleColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        numCalleColumn.setCellValueFactory(new PropertyValueFactory<>("num"));
        ciudadColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        codPostalColumn.setCellValueFactory(new PropertyValueFactory<>("cp"));
        telfColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
    }

    public void configureMechanicsColumnProperties(){
        idMechanicColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameMechaniColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void configureVehiclesColumnProperties(){
        licensePlateColumn.setCellValueFactory(new PropertyValueFactory<>("licensePlate"));
    }

    public void uploadVehiclesTable(){
        ObservableList<Vehicle> vehiclesList = FXCollections.observableArrayList();
        ArrayList<Vehicle> vehicles = Vehicle.allVehicles();

        vehiclesList.addAll(vehicles);

        tableLicensePlate.setItems(vehiclesList);
    }

    public void uploadClientTable(){

        ObservableList<Client> clientsList = FXCollections.observableArrayList();
        ArrayList<Client> clients = Client.allClient();

        clientsList.addAll(clients);

        tablaClientes.setItems(clientsList);
        tablaClientes.refresh();
        editPane.setVisible(false);
    }

    public void uploadMechanicTable(){
        ObservableList<Mechanic> mechanicsList = FXCollections.observableArrayList();
        ArrayList<Mechanic> mechanics = Mechanic.allMechanics();

        mechanicsList.addAll(mechanics);

        tableMechanic.setItems(mechanicsList);
    }

    public void editData(ActionEvent actionEvent) {

        if (!nombreEditado.getText().equals("")){
            client.setName(nombreEditado.getText());
        }
        if (!calleEditada.getText().equals("")){
            client.setStreet(calleEditada.getText());
        }
        if (!numCalleEditado.getText().equals("")){
            if (numCalleEditado.getText().matches("\\d+")) {
                client.setNum(Integer.parseInt(numCalleEditado.getText()));
            }
        }
        if (!ciudadEditada.getText().equals("")){
            client.setCity(ciudadEditada.getText());
        }
        if (!codPostEditado.getText().equals("")){
            if (codPostEditado.getText().matches("\\d+")) {
                client.setCp(codPostEditado.getText());
            }
        }
        if (!telfEditado.getText().equals("")){
            if (telfEditado.getText().matches("\\d+")) {
                client.setPhoneNumber(new String[]{telfEditado.getText()});
            }
        }

        client.updateClient();
        uploadClientTable();
    }

    public void deleteClient(ActionEvent actionEvent) {
        client.deleteClient();
        tablaClientes.refresh();
        editPane.setVisible(false);
    }


    public void openEditPane(MouseEvent mouseEvent) {
        if (tablaClientes.getSelectionModel().getSelectedItem()!=null) {
            editPane.setVisible(true);
            client = (Client) tablaClientes.getSelectionModel().getSelectedItem();
        }
    }

    public void addClient(ActionEvent actionEvent) {
        newClientPane.setVisible(true);
    }

    public void addNewClient(ActionEvent actionEvent) {


        if (verifyDataNewClient()) {
            errorDataNewClient.setVisible(false);
            String[] phoneNumberArray = convertPhoneNumberToArray();

            Client newClient = new Client(nombreNuevo.getText(), calleNueva.getText(), Integer.parseInt(numCalleNueva.getText()), ciudadNueva.getText(), codPostNuevo.getText(), phoneNumberArray);

            if (newClient.insertClient()) {
                addedClient.setVisible(true);
            }
        }else errorDataNewClient.setVisible(true);

    }

    public String[] convertPhoneNumberToArray(){
        return telfNuevo.getText().split(",");
    }

    public boolean verifyDataNewClient(){
        boolean dataCorrect = true;

        if (nombreNuevo.getText().equals("") || calleNueva.getText().equals("") || numCalleNueva.getText().equals("")
        || ciudadNueva.getText().equals("") || codPostNuevo.getText().equals("") || telfNuevo.getText().equals("")){
            dataCorrect = false;
        }

        if (!codPostNuevo.getText().matches("\\d+") || !numCalleNueva.getText().matches("\\d+")) {
            dataCorrect = false;
        }

        String[] phoneNumberArray = convertPhoneNumberToArray();

        for (int i = 0; i < phoneNumberArray.length; i++) {
            if (!phoneNumberArray[i].matches("\\d+")){
                dataCorrect = false;
            }
        }
        return dataCorrect;
    }

    public boolean verifyDataNewService(){
        boolean dataCorrect = true;

        if (!Vehicle.existVehicle(licenPlateServ.getText())) {
            dataCorrect = false;
        }

        if (serviceDescrip.getText().equals("")){
            dataCorrect = false;
        }

        if (!serviceCost.getText().matches("\\d+(\\.\\d+)?") || !serviceHour.getText().matches("\\d+(\\.\\d+)?")){
            dataCorrect = false;
        }

        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date utilDate = dateFormat.parse(repairDate.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Imprime la fecha resultante
            System.out.println("Fecha SQL convertida: " + sqlDate);

        } catch (ParseException e) {
            dataCorrect = false;
            System.out.println("Error al convertir la cadena a fecha: " + e.getMessage());
        }

        return dataCorrect;
    }

    public void backNewClient(ActionEvent actionEvent) {
        uploadClientTable();
        newClientPane.setVisible(false);
    }

    public void changeToRepairs(MouseEvent mouseEvent) {
        repairsPane.setVisible(true);
    }

    public void changeCursorRepair(MouseEvent mouseEvent) {
        repareLabel.setCursor(Cursor.HAND);
    }

    public void changeCursorDefault(MouseEvent mouseEvent) {
        repareLabel.setCursor(Cursor.DEFAULT);
    }

    public void assignService(ActionEvent actionEvent) throws ParseException {
        serviceCreated.setVisible(false);
        serviceError.setVisible(false);
        if (verifyDataNewService()){
            Service service = new Service(serviceDescrip.getText(),Float.parseFloat(serviceCost.getText()),Float.parseFloat(serviceHour.getText()));
            service.insertService();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            java.util.Date utilDate = dateFormat.parse(repairDate.getText());
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            Service.insertRepair(Service.idLastService(),licenPlateServ.getText(), sqlDate);

            serviceCreated.setVisible(true);
        }else serviceError.setVisible(true);
    }

    public void changeToClient(MouseEvent mouseEvent) {
        repairsPane.setVisible(false);
    }

    public void changeCursorClient(MouseEvent mouseEvent) {
        clientLabel1.setCursor(Cursor.HAND);
    }
}
