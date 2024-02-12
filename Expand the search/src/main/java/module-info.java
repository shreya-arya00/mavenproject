module org.example.expandthesearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.expandthesearch to javafx.fxml;
    exports org.example.expandthesearch;
}