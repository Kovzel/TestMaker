package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControllerExit {
    @FXML
    public Button buttonYes;
    public Button buttonNo;

    public void buttonAction(ActionEvent actionEvent) {
        if (((Button) actionEvent.getTarget()).getText().equals("Yes")) {
            System.exit(0);
        } else {
            Stage stage = (Stage) buttonNo.getScene().getWindow();
            stage.close();
        }
    }
}
