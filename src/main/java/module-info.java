module com.hit.controller {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.hit.userinterface to javafx.fxml;
    exports com.hit.userinterface;
}