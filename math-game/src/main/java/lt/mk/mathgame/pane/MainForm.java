package lt.mk.mathgame.pane;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lt.mk.mathgame.model.PlayResult;
import lt.mk.mathgame.service.ServiceManager;
import lt.mk.mathgame.utils.AlertUtils;

public class MainForm extends GridPane {

    public MainForm() {
        // Instantiate a new Grid Pane


        createMainFormPane();


        // Add UI controls to the registration form grid pane
        addUIControls();
    }

    private void createMainFormPane() {

        // Position the pane at the center of the screen, both vertically and horizontally
        setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        setHgap(10);

        // Set the vertical gap between rows
        setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200, 200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        getColumnConstraints().addAll(columnOneConstraints);
        getColumnConstraints().addAll(columnTwoConstrains);

    }


    private void addUIControls() {
        // Add Header
        Label headerLabel = new Label("Mokomės matematikos");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        add(headerLabel, 0, 0, 2, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));

        //playground
        PlayGround playGround = new PlayGround();
        add(playGround, 0, 1, 2, 1);

        // Add Submit Button
        Button submitButton = new Button("Atsakymas");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        add(submitButton, 0, 2, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0, 20, 0));

        //Add status bar
        PlayResult values = ServiceManager.settingsService().loadValues();
        StatusProgress statusProgress = new StatusProgress(values.getTotal(), values.getCorrect());
        add(statusProgress, 0, 3, 2, 1);


        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    if (playGround.getAnswer()) {
                        AlertUtils.correct(MainForm.this.getScene().getWindow(), playGround.getMessage());
                        statusProgress.incrementCorrect();
                    } else {
                        AlertUtils.incorrect(MainForm.this.getScene().getWindow(), playGround.getMessage());
                        statusProgress.incrementIncorrect();
                    }
                    playGround.nextPlay(ServiceManager.mathService().nextPlay());
                } catch (NumberFormatException ignore) {
                }
                try {
                    ServiceManager.settingsService().persistValues(statusProgress.getTotal(), statusProgress.getCorrect());
                } catch (Exception e) {

                }
            }
        });


        playGround.nextPlay(ServiceManager.mathService().nextPlay());
    }


}
