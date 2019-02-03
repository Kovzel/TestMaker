package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Test;

public class ControllerCreateQuestions {
    private int numberAnswers = 2;
    private TextField[] textFields = new TextField[9];
    private Test test = new Test();
    public TextArea textArea;
    public TextField textField1;
    public TextField textField2;
    public TextField textField3;
    public TextField textField4;
    public TextField textField5;
    public TextField textField6;
    public TextField textField7;
    public TextField textField8;
    public TextField textField9;
    private ControllerMain controllerMain;

    void initData(ControllerMain controllerMain) {
        this.controllerMain = controllerMain;
    }

    public void ready() throws Exception {
        saveQuestion();
        Stage popup = new Stage();
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.WINDOW_MODAL);
        popup.initOwner(textArea.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/paneTitle.fxml"));
        Scene scene = new Scene(loader.load(), 365, 192);
        loader.<ControllerTitle>getController().initData(test);
        loader.<ControllerTitle>getController().initData(controllerMain);
        popup.setScene(scene);
        popup.show();
    }

    public void reset() {
        clearPane();
        test.clearQuestions();
    }

    public void help() {

    }

    public void cancel() {
        test.clearQuestions();
        Stage stage = (Stage) textArea.getScene().getWindow();
        stage.close();
    }

    public void addQuestion() {
        saveQuestion();
        clearPane();
    }

    public void takeAwayQuestion() {
        if (test.getQuestions().size() > 0) {
            textArea.setText(test.getQuestions().get(test.getQuestions().size() - 1).getQuestion());
            numberAnswers = test.getQuestions().get(test.getQuestions().size() - 1).getNumberAnswerChoices();
            createPosition(numberAnswers);
            for (int i = 0; i < numberAnswers; ++i) {
                textFields[i].setText(test.getQuestions().get(test.getQuestions().size() - 1).getAnswerChoices().get(i));
            }
            test.removeLastQuestion();
        }
    }

    private void saveQuestion() {
        test.createQuetions();
        initAnswers();
        test.setQuestion(textArea.getText());
        test.setNumberAnswerChoices(numberAnswers);
        for (int i = 0; i < numberAnswers; ++i) {
            test.setAnswers(textFields[i].getText());
        }
    }

    private void clearPane() {
        initAnswers();
        for (int i = 0; i < textFields.length; ++i) {
            textFields[i].setText("");
        }
        textArea.setText("");
    }

    private void initAnswers() {
        textFields[0] = textField1;
        textFields[1] = textField2;
        textFields[2] = textField3;
        textFields[3] = textField4;
        textFields[4] = textField5;
        textFields[5] = textField6;
        textFields[6] = textField7;
        textFields[7] = textField8;
        textFields[8] = textField9;
    }

    public void changeNumberAnswers(ActionEvent actionEvent) {
        if (((MenuItem) actionEvent.getTarget()).getText().equals("+ Answer")) {
            if (numberAnswers < 9) {
                ++numberAnswers;
                createPosition(numberAnswers);
            }
        } else {
            if (numberAnswers > 2) {
                initAnswers();
                for (TextField answer : textFields) {
                    if (Integer.parseInt(answer.getId().substring(9)) == numberAnswers) {
                        answer.setText("");
                    }
                }
                --numberAnswers;
                createPosition(numberAnswers);
            }
        }
    }


    private void createPosition(int numberQuestions) {
        switch (numberQuestions) {
            case 2:
                defaultPositions3();
                textField3.setVisible(false);
                textField4.setVisible(false);
                setProperties(textField1, 14, 156, 93.5, 435);
                setProperties(textField2, 14, 277, 93.5, 435);
                break;
            case 3:
                defaultPositions3();
                textField4.setVisible(false);
                setProperties(textField3, 129, 277, 93.5, 204.5);
                break;
            case 4:
                defaultPositions3();
                break;
            case 5:
                defaultPositions2();
                textField6.setVisible(false);
                setProperties(textField4, 89, 277, 93.5, 133);
                setProperties(textField5, 241, 277, 93.5, 133);
                break;
            case 6:
                defaultPositions2();
                break;
            case 7:
                defaultPositions1();
                textField8.setVisible(false);
                textField9.setVisible(false);
                textField7.setLayoutX(165);
                break;
            case 8:
                defaultPositions1();
                textField9.setVisible(false);
                textField8.setLayoutX(241);
                textField7.setLayoutX(89);
                break;
            case 9:
                defaultPositions1();
                break;
        }
    }

    private void setProperties(TextField textField, boolean vis, int X, int Y, double prefH, double prefW) {
        textField.setLayoutX(X);
        textField.setLayoutY(Y);
        textField.setPrefHeight(prefH);
        textField.setPrefWidth(prefW);
        textField.setVisible(vis);
    }

    private void setProperties(TextField textField, int X, int Y, double prefH, double prefW) {
        textField.setLayoutX(X);
        textField.setLayoutY(Y);
        textField.setPrefHeight(prefH);
        textField.setPrefWidth(prefW);
    }

    private void defaultPositions3() {
        defaultPositions2();
        textField5.setVisible(false);
        textField6.setVisible(false);
        setProperties(textField1, 14, 156, 93.5, 204.5);
        setProperties(textField2, 244, 156, 93.5, 204.5);
        setProperties(textField3, 14, 277, 93.5, 204.5);
        setProperties(textField4, 244, 277, 93.5, 204.5);
    }

    private void defaultPositions2() {
        defaultPositions1();
        textField7.setVisible(false);
        textField8.setVisible(false);
        textField9.setVisible(false);
        setProperties(textField1, 14, 156, 93.5, 133);
        setProperties(textField2, 165, 156, 93.5, 133);
        setProperties(textField3, 316, 156, 93.5, 133);
        setProperties(textField4, 14, 277, 93.5, 133);
        setProperties(textField5, 165, 277, 93.5, 133);
        setProperties(textField6, 316, 277, 93.5, 133);
    }

    private void defaultPositions1() {
        setProperties(textField1, true, 14, 156, 53, 133);
        setProperties(textField2, true, 165, 156, 53, 133);
        setProperties(textField3, true, 316, 156, 53, 133);
        setProperties(textField4, true, 14, 237, 53, 133);
        setProperties(textField5, true, 165, 237, 53, 133);
        setProperties(textField6, true, 316, 237, 53, 133);
        setProperties(textField7, true, 14, 318, 53, 133);
        setProperties(textField8, true, 165, 318, 53, 133);
        setProperties(textField9, true, 316, 318, 53, 133);
    }
}
