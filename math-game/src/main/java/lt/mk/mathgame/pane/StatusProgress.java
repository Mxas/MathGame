package lt.mk.mathgame.pane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StatusProgress extends GridPane {


    private static final String DEFAULT = "-fx-padding: 5;-fx-border-style: solid inside;";
    private static final String RED_BAR = DEFAULT+"-fx-accent: red;";
    private static final String YELLOW_BAR = DEFAULT+"-fx-accent: yellow;";
    private static final String ORANGE_BAR = DEFAULT+"-fx-accent: orange;";
    private static final String GREEN_BAR = DEFAULT+"-fx-accent: green;";

    private int total;
    private int correct;

    private Label totalLbl = new Label();
    private Label correctLbl = new Label();

    private Label incorrectLbl = new Label();
    private final ProgressBar progress = new ProgressBar();

    public StatusProgress(int total, int correct) {
        this.total = total;
        this.correct = correct;

        setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        setPadding(new Insets(40, 20, 40, 20));

        // Set the horizontal gap between columns
        setHgap(10);

        // Set the vertical gap between rows
        setVgap(10);

        // Add Column Constraints

        getColumnConstraints().addAll(new ColumnConstraints(50, 70, Double.MAX_VALUE, Priority.ALWAYS, HPos.LEFT, true));
        getColumnConstraints().addAll(new ColumnConstraints(100, 100, Double.MAX_VALUE, Priority.ALWAYS, HPos.CENTER, true));
        getColumnConstraints().addAll(new ColumnConstraints(130, 130, Double.MAX_VALUE, Priority.ALWAYS, HPos.RIGHT, true));


        addControls();

        initNumbers();
    }

    private void addControls() {

        progress.setMaxWidth(Double.MAX_VALUE);
        add(progress, 0, 0, 3, 1);


        totalLbl.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        add(totalLbl, 0, 1);

        correctLbl.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        correctLbl.setTextFill(Color.GREEN);
        add(correctLbl, 1, 1);

        incorrectLbl.setFont(Font.font("Arial", FontWeight.BOLD, 17));
        incorrectLbl.setTextFill(Color.RED);
        add(incorrectLbl, 2, 1);

        progressColor();
    }

    private void progressColor() {
        progress.setStyle(GREEN_BAR);
        progress.progressProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double progressProc = newValue == null ? 0 : newValue.doubleValue();
                if (progressProc < 0.2) {
                    setBarStyleClass(progress, RED_BAR);
                } else if (progressProc < 0.4) {
                    setBarStyleClass(progress, ORANGE_BAR);
                } else if (progressProc < 0.75) {
                    setBarStyleClass(progress, YELLOW_BAR);
                } else {
                    setBarStyleClass(progress, GREEN_BAR);
                }
            }

            private void setBarStyleClass(ProgressBar bar, String style) {
                bar.setStyle(style);
            }
        });
    }


    private void initNumbers() {
        totalLbl.setText("Iš viso: " + this.total);
        correctLbl.setText("Teisingi: " + this.correct);
        incorrectLbl.setText("Neteisingai: " + (this.total - this.correct));

        if (this.correct > 0) {
            progress.setProgress((double) this.correct / (double) this.total);
        } else {
            progress.setProgress(ProgressBar.INDETERMINATE_PROGRESS);
        }

    }

    public void incrementCorrect() {
        this.total++;
        this.correct++;
        initNumbers();
    }

    public void incrementIncorrect() {
        this.total++;
        initNumbers();
    }

    public int getTotal() {
        return total;
    }

    public int getCorrect() {
        return correct;
    }
}
