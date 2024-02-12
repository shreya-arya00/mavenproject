module org.example.searchstartegies {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.searchstartegies to javafx.fxml;
    exports org.example.searchstartegies;
}