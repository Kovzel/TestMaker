package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Test;
import sample.TestHome;

public class ControllerSelection {
    @FXML
    private TableView<Test> tableViewHistory;
    @FXML
    private TableColumn<Test, String> tests;
    @FXML
    private TableColumn<Test, Integer> id;
    @FXML
    private TableColumn<Test, Integer> views;
    @FXML
    private TableColumn<Test, Integer> questions;
    @FXML
    private TableColumn<Test, Double> rating;

    private ControllerMain controllerMain;

    void initData(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }

    public void deleteTest() {
        try {
            TableView.TableViewSelectionModel<Test> selectionModel = tableViewHistory.getSelectionModel();
            long id = selectionModel.getSelectedItem().getId();
            TestHome.deleteTest(TestHome.getTest(id));
            tableViewHistory.getItems().remove(selectionModel.getSelectedIndex());
        } catch (Exception ignored) {
        }
    }

    public void requestOpenTest() {
        TableView.TableViewSelectionModel<Test> selectionModel = tableViewHistory.getSelectionModel();
        long id = selectionModel.getSelectedItem().getId();
        controllerMain.openTest(TestHome.getTest(id));
        Stage stage = (Stage) tableViewHistory.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void initialize() {
        tests.setCellValueFactory(new PropertyValueFactory<>("name"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        views.setCellValueFactory(new PropertyValueFactory<>("views"));
        questions.setCellValueFactory(new PropertyValueFactory<>("countQuestions"));
        rating.setCellValueFactory(new PropertyValueFactory<>("rating"));
        tableViewHistory.setItems(TestHome.getTestRecords());
    }
}
