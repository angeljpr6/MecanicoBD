module com.example.mecanicobd {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.mecanicobd to javafx.fxml;
    exports com.example.mecanicobd;
    exports com.example.mecanicobd.view;
    opens com.example.mecanicobd.view to javafx.fxml;
    exports com.example.mecanicobd.model;
    opens com.example.mecanicobd.model to javafx.fxml;
}