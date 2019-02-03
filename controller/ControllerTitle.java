package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import sample.Test;
import sample.TestHome;

public class ControllerTitle {
    @FXML
    public TextArea textArea;
    public Button save;
    public Button cancel;
    private Test test;
    private ControllerMain controllerMain;

    void initData(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }

    void initData(Test test) {
        this.test = test;
    }

    public void save() {
        test.setName(textArea.getText());
        TestHome.saveTest(test);
        Stage stage = (Stage) save.getScene().getWindow();
        ((Stage) stage.getOwner()).close();
        controllerMain.proposeOpenTest(TestHome.getTest(TestHome.getLastId()));
    }

    public void cancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}