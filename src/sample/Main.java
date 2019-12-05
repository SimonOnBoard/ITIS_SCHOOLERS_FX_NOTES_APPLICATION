package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.Controller;
import sample.models.Note;

public class Main extends Application {

    public static Controller mainScenecontroller;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/scenes/sample.fxml"));
        primaryStage.setTitle("Мои заметки");
        primaryStage.setScene(new Scene(root, 900,700));
        primaryStage.show();
    }

    public static ObservableList<Note> loadNotes() {
        Note note = new Note("Name","Text","Category",1L);
        Note note1 = new Note("Name1","Text1","Category1", 2L);
        Note note2 = new Note("Name2","Text2","Category2", 3L);
        ObservableList<Note> notes = FXCollections.observableArrayList(note, note1, note2);
        return notes;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
