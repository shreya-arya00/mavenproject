module org.example.invertedindexsearch {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.invertedindexsearch to javafx.fxml;
    exports org.example.invertedindexsearch;
}