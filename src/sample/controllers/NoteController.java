package sample.controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Note;
import sample.services.FileWorker;
import sample.services.IdFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class NoteController implements Initializable {
    @FXML
    public TextField name;
    @FXML
    public TextArea text;
    @FXML
    public TextField category;
    @FXML
    public Button saveButton;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        saveButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Note note = new Note(name.getText(), text.getText(), category.getText(), IdFactory.getNextId());
                FileWorker.write(note);
                Main.mainScenecontroller.listView.getItems().add(note);
                Main.mainScenecontroller.notes.add(note);
                Stage stage = (Stage) saveButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
