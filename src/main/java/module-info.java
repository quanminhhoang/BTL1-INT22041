module com.example.dictionaryapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jsapi;


    opens com.example.dictionaryapp to javafx.fxml;
    exports com.example.dictionaryapp;
}