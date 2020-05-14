package lt.mk.mathgame.pane;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class AnswerDialog extends Dialog<String> {
    private BorderPane container = new BorderPane();
    private HBox top = new HBox();
    private ImageView imageView = new ImageView();
    private HBox bottom = new HBox();
    private Label headerLabel = new Label();

    public AnswerDialog(Image image, String message) {
        setResizable(true);

        setHeight(400);
        setWidth(500);

        updateGrid();
        imageView.setImage(image);

        imageView.fitWidthProperty().bind(this.widthProperty());
        imageView.fitHeightProperty().bind(this.heightProperty());
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        headerLabel.setText(message);
    }

    private void updateGrid() {


        container.getChildren().clear();

        // Set a padding of 20px on each side
        container.setPadding(new Insets(0, 0, 5, 5));

        container.setTop(top);
        container.setCenter(imageView);
        container.setBottom(bottom);

        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        bottom.getChildren().add(headerLabel);
        GridPane.setHalignment(headerLabel, HPos.CENTER);


        getDialogPane().setContent(container);
        getDialogPane().getButtonTypes().setAll(ButtonType.OK);
    }
}
