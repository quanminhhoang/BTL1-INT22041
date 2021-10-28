package com.example.dictionaryapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class Contronler extends Dictionary implements Initializable {
    @FXML
    private TextArea ExplainArea;
    @FXML
    private TextArea EditArea;
    @FXML
    private TextField TextToSpeech;
    @FXML
    private TextField add_Target;
    @FXML
    private TextField add_Explain;
    @FXML
    private TextField SearchField;
    @FXML
    private TextField SearchToDelete;
    @FXML
    private TextField ToTrans;
    @FXML
    private TextField Trans;
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
    private Pane Pane;
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
    private ImageView catto;
    @FXML
    private Button ConfirmEdit;
    @FXML
    private Button CancelEdit;


    /**
     * Exit the program.
     */
    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * hien nghia tren textArea.
     */
    public void LookupWord() {
        if (SearchField.getText() == null || SearchField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in SearchField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String WordLook = SearchField.getText();
            if (DictionaryManagement.WordExist(WordLook)) {
                String text = DictionaryManagement.DictionaryLookup(WordLook);
                ExplainArea.setText(text);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Word don't exist");
                alert.setContentText("Please enter words.");
                alert.showAndWait();
            }
        }
    }

    public void WordToTLAPI() throws IOException {
        if (DictionaryManagement.netIsAvailable() && (ToTrans.getText() == null || ToTrans.getText().isEmpty())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        }else if (!DictionaryManagement.netIsAvailable())
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

    public void ConfirmEdit1() throws IOException {
        String Word = SearchField.getText();
        String Explain = EditArea.getText();
        String Out = DictionaryManagement.Modify(Word, Explain);
        ExplainArea.setText(Out);
        EditArea.setVisible(false);
        ExplainArea.setVisible(true);
        ConfirmEdit.setVisible(false);
        CancelEdit.setVisible(false);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText("Edited with new meaning");
        alert.showAndWait();
    }
    public void CancelEdit1() {
        EditArea.clear();
        EditArea.setVisible(false);
        ExplainArea.setVisible(true);
        ConfirmEdit.setVisible(false);
        CancelEdit.setVisible(false);
    }

    public void EditExplain() {
        if(SearchField.getText() == null || SearchField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing to edit");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String Explain = ExplainArea.getText();
            ExplainArea.setVisible(false);
            EditArea.setVisible(true);
            CancelEdit.setVisible(true);
            ConfirmEdit.setVisible(true);
            EditArea.setText(Explain);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText("Enter the new meaning in the ExplainArea");
            alert.showAndWait();
        }
    }

    public void ToDeletePane() {
        if (SearchField.getText() == null || SearchField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in search field to delete");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            ExplainArea.clear();
            String Word = SearchField.getText();
            CancelEdit1();
            SetPaneDeleteVisible();
            SearchToDelete.setText(Word);
        }
    }

    public void TTS() {
        if (TextToSpeech.getText().isEmpty() || TextToSpeech.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String wordLook = TextToSpeech.getText();
            DictionaryManagement.TTS(wordLook);
        }
    }
    public void TTS2() {
        if (SearchField.getText().isEmpty() || SearchField.getText() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Nothing in TextField");
            alert.setContentText("Please enter words.");
            alert.showAndWait();
        } else {
            String wordLook = SearchField.getText();
            DictionaryManagement.TTS(wordLook);
        }
    }


    public void InputSearch() {
            String text = SearchField.getText();
            List<String> list = DictionaryManagement.DictionarySearch(text);
            Collections.sort(list);
            ObservableList<String> input = FXCollections.observableArrayList(list);
            listView.setItems(input);
            DictionaryManagement.add_up.clear();
    }

    // ham kich chuot vao tu trong textArea va cho ra nghia
    public void ClickToExplain() {
        try {
            SearchField.setText(listView.getSelectionModel().getSelectedItem().toString());
            ExplainArea.setText(DictionaryManagement.DictionaryLookup(listView.getSelectionModel().getSelectedItem().toString()));
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }
    }

    // xoa tu cua textFied
    public void EraseSearch() {
        SearchField.setText("");
        ExplainArea.setText("");
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
        Pane.setVisible(false);
    }

    public void SetPaneHomeVisible() {
        CancelEdit1();
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(true);
        Pane.setVisible(false);
    }

    public void SetPaneDeleteVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(true);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        Pane.setVisible(false);
    }

    public void SetPaneTransVisible() {
        PaneTrans.setVisible(true);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        Pane.setVisible(false);
    }

    public void SetPaneInfoVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(false);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        Pane.setVisible(true);
    }

    public void SetPaneSpeechVisible() {
        PaneTrans.setVisible(false);
        PaneSpeech.setVisible(true);
        PaneDelete.setVisible(false);
        PaneAddWord.setVisible(false);
        PaneHome.setVisible(false);
        Pane.setVisible(false);
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
