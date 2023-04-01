module com.example.hospital {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.mail;

    opens com.example.hospital to javafx.fxml;
    exports com.example.hospital;
    opens com.example.hospital.stuff to javafx.base;
}