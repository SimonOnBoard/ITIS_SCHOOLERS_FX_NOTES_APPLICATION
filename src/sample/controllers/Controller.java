package sample.controllers;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.Main;
import sample.models.Note;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public Pane pane;
    @FXML
    public Button addButton;
    @FXML
    public ListView<Note> listView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Note> noteList = Main.loadNotes();
        //listView.setCellFactory(listView -> new NoteView());
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        listView.getItems().addAll(noteList);
        Main.mainScenecontroller = this;
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Parent root = null;
                Stage stage = new Stage();
                try {
                    root = FXMLLoader.load(getClass().getResource("/sample/scenes/newNote.fxml"));
                    stage.setTitle("Добавить заметку");
                    stage.setScene(new Scene(root, 800,650));
                    stage.show();
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        });
    }

    @FXML
    public void handleMouseClick(MouseEvent mouseEvent) {
        Note note = listView.getSelectionModel().getSelectedItem();
        if(note != null){
            Parent root = null;
            Stage stage = new Stage();
            FXMLLoader loader;
            try {
                loader = new FXMLLoader(getClass().getResource("/sample/scenes/showNote.fxml"));
                root = loader.load();
                ShowNoteController controller = loader.getController();
                controller.setData(note);
                stage.setTitle("Заметка");
                stage.setScene(new Scene(root, 900,700));
                stage.show();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
