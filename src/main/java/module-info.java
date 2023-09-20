module jewel.ca1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires xstream;


    opens jewel.ca1 to javafx.fxml;
    exports jewel.ca1;
}