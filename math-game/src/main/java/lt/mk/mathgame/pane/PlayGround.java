package lt.mk.mathgame.pane;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import lt.mk.mathgame.model.PlayValues;
import lt.mk.mathgame.pane.text.LimitedTextField;

public class PlayGround extends GridPane {

    private TextField firstNumber;
    private Label operation;
    private TextField secondNumber;
    private LimitedTextField answer;
    private int realAnswer;

    public PlayGround() {
        setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        setHgap(10);

        // Set the vertical gap between rows
        setVgap(10);

        // Add Column Constraints

        getColumnConstraints().addAll(new ColumnConstraints(20, 50, Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true));
        getColumnConstraints().addAll(new ColumnConstraints(10, 20, 20, Priority.ALWAYS, HPos.CENTER, true));
        getColumnConstraints().addAll(new ColumnConstraints(20, 50, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, false));
        getColumnConstraints().addAll(new ColumnConstraints(10, 20, 20, Priority.ALWAYS, HPos.CENTER, true));
        getColumnConstraints().addAll(new ColumnConstraints(20, 50, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, false));


        addControls();
    }

    private void addControls() {


        // Add Text Field

        firstNumber = new TextField();
        firstNumber.setPrefHeight(10);
        firstNumber.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        firstNumber.setEditable(false);

        add(firstNumber, 0, 1);


        // Add Header

        operation = new Label("+");
        operation.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        add(operation, 1, 1);
        GridPane.setHalignment(operation, HPos.CENTER);
        GridPane.setMargin(operation, new Insets(20, 0, 20, 0));


        // Add Text Field
        secondNumber = new TextField();
        secondNumber.setPrefHeight(10);
        secondNumber.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        secondNumber.setEditable(false);
        add(secondNumber, 2, 1);


        // Add Header
        Label headerLabel = new Label("=");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        add(headerLabel, 3, 1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0, 20, 0));


        // Add Text Field
        answer = new LimitedTextField();
        answer.setPrefHeight(5);
        answer.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        answer.setEditable(true);
        answer.setRestrict("-?[0-9]");
        answer.setMaxLength(5);
        answer.setTooltip(new Tooltip("Tik skaičiai"));

        add(answer, 4, 1);

    }


    public void nextPlay(PlayValues playValues) {

        firstNumber.setText(playValues.getFirst() + "");
        operation.setText(playValues.getOperation().sign());
        secondNumber.setText(playValues.getSecond() + "");

        answer.setText("");
        realAnswer = playValues.getAnswer();
    }


    public boolean getAnswer() {
        return Integer.parseInt(answer.getText()) == realAnswer;
    }

    public String getMessage() {
        return firstNumber.getText() + " " + operation.getText() + " " + secondNumber.getText() + " = " + realAnswer;
    }
}
