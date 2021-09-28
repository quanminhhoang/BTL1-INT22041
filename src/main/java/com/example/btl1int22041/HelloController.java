package com.example.btl1int22041;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    static int count = 0;

    @FXML
    public TextField tfInput;

    @FXML
    public Button btSearch;

    @FXML
    public TextArea taMeaning;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        taMeaning.setText("xin chao");
        tfInput.setText("nhap tu");
        btSearch.setOnMouseClicked(event -> {
            count++;
            taMeaning.setText("vua nhap "+count+" lan");
        });
    }
}