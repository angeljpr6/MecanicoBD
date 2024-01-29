module com.example.mecanicobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mecanicobd to javafx.fxml;
    exports com.example.mecanicobd;
}