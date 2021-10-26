package com.example.dictionaryapp;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Contronler extends Dictionary implements Initializable {
    @FXML
    private TextArea textArea;
    @FXML
    private TextField TextToP;
    @FXML
    private TextField add_Target;
    @FXML
    private TextField add_Explain;
    @FXML
    private TextField searchField;
    @FXML
    private TextField SearchToDelete;
    @FXML
    private TextField ToTrans;
    @FXML
    private TextField Trans;
    @FXML
    private Button TLAPI;
    @FXML
    private Button TTS;
    @FXML
    private Button TTS2;
    @FXML
    private Button AddWord;
    @FXML
    private Button Home;
    @FXML
    private Button ChangeMode;
    @FXML
    private Button ToDeletePane;
    @FXML
    private javafx.scene.control.Button closeButton;
    @FXML
    private Pane PaneAddWord;
    @FXML
    private Pane PaneHome;
    @FXML
    private Pane PaneDelete;
    @FXML
    private Pane PaneSpeech;
    @FXML
    private Pane PaneTrans;
    @FXML
    private Pane PaneInfo;
    @FXML
    public ListView listView;
    @FXML
    private ImageView e1;
    @FXML
    private ImageView e2;
    @FXML
    private ImageView v1;
    @FXML
    private ImageView v2;


    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    //ham su dung khi danh mot tu//an enter se hien thang nghia tren textArea
    public void wordlookup(ActionEvent event) {
        String wordLook = searchField.getText();
        String text = DictionaryManagement.DictionaryLookup(wordLook);
        if (Objects.equals(text, "//404//")) {
            textArea.setText("Not Found");
        } else {
            textArea.setText(text);
        }
    }
    public void WordToTLAPI() throws IOException {
        if (DictionaryManagement.netIsAvailable() && (ToTrans.getText() == null || ToTrans.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        }else if (DictionaryManagement.netIsAvailable()==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Internet");
            alert.setContentText("Please check your Internet connection again");
            alert.showAndWait();
        }
        else {
            String WordLook = ToTrans.getText();
            Trans.setText(DictionaryManagement.Translate(WordLook));
        }
    }

    boolean Mode = true;
    public void ChangeMode() {
        if(Mode) {
            Mode = false;
            v1.setVisible(false);
            e2.setVisible(true);
            v2.setVisible(true);
            e1.setVisible(false);
        } else {
            Mode = true;
            v1.setVisible(true);
            e2.setVisible(false);
            v2.setVisible(false);
            e1.setVisible(true);
        }
    }

    public void ToDeletePane() {
        if (searchField.getText() == null || searchField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in search field to delete");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String Word = searchField.getText();
            SetPaneDeleteVisible();
            SearchToDelete.setText(Word);
        }
    }

    public void TTS() {
        if (TextToP.getText().isEmpty() || TextToP.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String wordLook = TextToP.getText();
            DictionaryManagement.TTS(wordLook);
            TextToP.clear();
        }
    }
    public void TTS2() {
        if (searchField.getText().isEmpty() || searchField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String wordLook = searchField.getText();
            DictionaryManagement.TTS(wordLook);
            searchField.clear();
        }
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
            alert.setHeaderText("Do you want to change the word ");
            alert.showAndWait();
            String Explain = textArea.getText();
            String Word = searchField.getText();
            String Out = DictionaryManagement.Modified(Word, Explain);
            textArea.setText(Out);
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

    public void AddWord(){
        if (add_Target.getText() == null || add_Explain.getText() == null || add_Explain.getText().isEmpty() || add_Target.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You have not entered the word");
            alert.setContentText("Please add word");
            alert.showAndWait();
        } else {
            String target = add_Target.getText();
            String explain = add_Explain.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Add Word");
            alert.setHeaderText("Added New Word  " + target + " : " + explain );
            alert.showAndWait();
            List<String> s = DictionaryManagement.AddWord(target, explain);
            ObservableList<String> input = FXCollections.observableArrayList(s);
            listView.setItems(input);
            s.clear();
            add_Target.clear();
            add_Explain.clear();
        }
    }

    public void DeleteWord() {
        if (SearchToDelete.getText() == null || SearchToDelete.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("You have not entered the word ");
            alert.setContentText("Please add word");
            alert.showAndWait();
        } else if (DictionaryManagement.WordExist(SearchToDelete.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Word don't exist");
            alert.setContentText("Please try again");
            alert.showAndWait();
        } else {
            //Chua viet xong ham Dictionarymanegement.Remove..
            String Word = SearchToDelete.getText();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Delete");
            alert.setHeaderText("Delete Word : " + Word);
            alert.showAndWait();
            List<String> s = DictionaryManagement.RemoveWordFromDictionary(Word);
            ObservableList<String> input = FXCollections.observableArrayList(s);
            listView.setItems(input);
            s.clear();
            SearchToDelete.clear();

        }

    }

    public void SetPaneAddWordVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneHome.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(true);
        PaneInfo.setVisible(false);
    }

    public void SetPaneHomeVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(true);
        PaneInfo.setVisible(false);
    }

    public void SetPaneDeleteVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(true);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        PaneInfo.setVisible(false);
    }

    public void SetPaneTransVisible() {
        PaneTrans.setVisible(true);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        PaneInfo.setVisible(false);
    }

    public void SetPaneInfoVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        PaneInfo.setVisible(true);
    }

    public void SetPaneSpeechVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(true);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        PaneInfo.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            DictionaryManagement.InsertFromFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // danh sach tu o ListView
        Collections.sort(listWordTarget);
        ObservableList<String> data = FXCollections.observableArrayList(listWordTarget);
        listView.setItems(data);
    }

}
