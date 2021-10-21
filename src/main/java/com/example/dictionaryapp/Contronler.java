package com.example.dictionaryapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Contronler extends Dictionary implements Initializable {
    @FXML
    private MenuBar menuBar;
    @FXML
    private TextArea textArea;
    @FXML
    private TextField add_Target;
    @FXML
    private TextField add_Explain;
    @FXML
    public ListView listView;
    @FXML
    private TextField searchField;
    @FXML
    private Button erase;
    @FXML
    private Button TLAPI;
    @FXML
    private Button TTS;

    //ham su dung khi danh mot tu//an enter se hien thang nghia tren textArea
    public void wordlookup(ActionEvent event) {
        String wordLook = searchField.getText();
        textArea.setText(DictionaryManagement.DictionaryLookup(wordLook));
    }

    public void WordToTLAPI() throws IOException {
        String WordLook = searchField.getText();
        textArea.setText(DictionaryManagement.TranslateEnVi(WordLook));
    }

    public void TTS() {
        String wordLook = searchField.getText();
        DictionaryManagement.TTS(wordLook);
    }

    public void inputsearch(KeyEvent event) {
        String se = searchField.getText();
        List<String> list = DictionaryManagement.DictionarySearch(se);
        Collections.sort(list);
        ObservableList<String> input = FXCollections.observableArrayList(list);
        listView.setItems(input);
        DictionaryManagement.add_up.clear();

    }

    // ham kich chuot vao tu trong textArea va cho ra nghia
    public void clicked(MouseEvent event) {
        try {
            searchField.setText(listView.getSelectionModel().getSelectedItem().toString());
            textArea.setText(DictionaryManagement.DictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        } catch (NullPointerException e1) {
            System.out.println("There is nothing");
        }
    }

    public void mode_word(KeyEvent event) throws IOException {
        textArea.setEditable(true);
        if (event.getCode() == KeyCode.ENTER) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Warning");
            alert.setHeaderText("do you want to change the word ");
            alert.show();
            String moding = textArea.getText();
            String poin = searchField.getText();
            String st = DictionaryManagement.Modified(poin, moding);
            textArea.setText(st);
            textArea.setEditable(false);
        }
    }


    // xoa tu cua textFied
    public void erase_search(ActionEvent event) {
        searchField.setText("");
        textArea.setText("");
    }

    // tat chuong trinh
    public void close(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    // them tu moi, su dung Edit
    public void insert_newWord(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SHIFT) {
            String terget = add_Target.getText();
            String explain = add_Explain.getText();
            if (terget.length() > 0 && explain.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Add new Words");
                alert.setHeaderText("You definitely want more ");
                alert.setContentText("You are adding this word " + '"' + terget + '"' + " and its meaning " + '"' + explain + '"');
                alert.showAndWait();
                List<String> s = DictionaryManagement.Addmoreword(terget, explain);
                ObservableList<String> input = FXCollections.observableArrayList(s);
                listView.setItems(input);
                DictionaryManagement.EditFile();
                s.clear();
                add_Target.clear();
                add_Explain.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("You have not entered the word ");
                alert.setContentText("Please add words correctly.");
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            DictionaryManagement.InsertFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // danh sach tu o ListView
        Collections.sort(listWordTarget);
        ObservableList<String> data = FXCollections.observableArrayList(listWordTarget);
        listView.setItems(data);
    }
    
}
