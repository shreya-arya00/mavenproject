module org.example.stringtheory {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.stringtheory to javafx.fxml;
    exports org.example.stringtheory;
}