package lt.mk.mathgame.utils;

import java.io.InputStream;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Window;
import lt.mk.mathgame.pane.AnswerDialog;
import lt.mk.mathgame.service.ServiceManager;

public class AlertUtils {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void correct(Window owner, String message) {
        Optional<InputStream> pic = ServiceManager.picService().nextCorrectIS();
        if (pic.isPresent()) {
            showAlert(owner, "Teisingai!!!", "Labai gerai :)", message, pic.get());
        } else {
            showAlert(owner, "Teisingai!!!", "Labai gerai :)", message);
        }
    }

    public static void incorrect(Window owner, String message) {
        Optional<InputStream> pic = ServiceManager.picService().nextIncorrectIS();
        if (pic.isPresent()) {
            showAlert(owner, "Neteisingai!!!", "Reikėtų pasistengti :/", message, pic.get());
        } else {
            showAlert(owner, "Neteisingai!!!", "Reikėtų pasistengti :/", message);
        }
    }

    public static void showAlert(Window owner, String tile, String message, String headerText, InputStream pic) {
        AnswerDialog alert = new AnswerDialog(new Image(pic), headerText);
        alert.setTitle(tile);
        alert.setHeaderText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }

    public static void showAlert(Window owner, String tile, String message, String headerText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(tile);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.showAndWait();
    }

}
