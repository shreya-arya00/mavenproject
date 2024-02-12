module org.example.usermenu {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.usermenu to javafx.fxml;
    exports org.example.usermenu;
}