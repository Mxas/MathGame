package lt.mk.mathgame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import lt.mk.mathgame.pane.MainForm;
import lt.mk.mathgame.pane.PlayGround;
import lt.mk.mathgame.pane.StatusProgress;
import lt.mk.mathgame.service.ServiceManager;
import lt.mk.mathgame.utils.AlertUtils;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //  Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Matematika");
        primaryStage.setScene(new Scene(new MainForm(), 600, 450));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
