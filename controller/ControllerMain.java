package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sample.Test;
import sample.TestHome;

import java.util.ArrayList;

public class ControllerMain {
    @FXML
    public Button button1;
    public Button button2;
    public Button button3;
    public Button button4;
    public Button button5;
    public Button button6;
    public Button button7;
    public Button button8;
    public Button button9;
    public Label label;
    private ArrayList<Integer> numberAnswer = new ArrayList<>();
    private Button buttons[] = new Button[9];
    private Test selectedTest;
    private boolean startAnswers = true;
    private int selectedQuestion = 0;
    private boolean testIsRated = false;

    public void buttonAction(ActionEvent actionEvent) throws Exception {
        numberAnswer.add(Integer.parseInt(((Button) actionEvent.getSource()).getId().substring(6)));
        if (testIsRated) {
            TestHome.setRatingAndViews(selectedTest, numberAnswer.get(numberAnswer.size() - 1));
            startAnswers = true;
            setQuestion(2, "Thank you for rating! How about something else?", "Choose test", "Create test");
            testIsRated = false;
            selectedTest = null;
        } else {
            if (!startAnswers) {
                if (numberAnswer.get(0) == 1 && !selectedTest.getQuestions().isEmpty() && selectedQuestion < selectedTest.getQuestions().size()) {
                    setQuestion(selectedTest.getQuestions().get(selectedQuestion).getAnswerChoices().size(), selectedTest.getQuestions().get(selectedQuestion).getQuestion(), selectedTest.getQuestions().get(selectedQuestion).getAnswerChoices());
                    selectedQuestion++;
                } else if (selectedQuestion == selectedTest.getQuestions().size()) {
                    setQuestion(5, "Rate test \"" + selectedTest.getName() + "\" from 1 to 5, ", "1", "2", "3", "4", "5");
                    testIsRated = true;
                    numberAnswer.clear();
                } else {
                    startAnswers = true;
                    numberAnswer.clear();
                    setQuestion(2, "And what do you want?", "Choose test", "Create test");
                }
            } else {
                if (numberAnswer.get(numberAnswer.size() - 1) == 1) {
                    findTests();
                } else {
                    createQuestions();
                }
            }
        }
    }

    void proposeOpenTest(Test test) {
        selectedTest = test;
        setStandart("created", "want to try to pass it");
    }

    public void restart() {
        if (selectedTest != null) {
            setStandart("restarted", "are ready");
        } else {
            setQuestion(2, "You haven't opened any of the tests!", "Сhoose test", "Сreate test");
        }
    }

    void openTest(Test test) {
        selectedTest = test;
        setStandart("opened", "are ready");
    }

    private void setStandart(String beg, String cont) {
        numberAnswer.clear();
        startAnswers = false;
        selectedQuestion = 0;
        setQuestion(2, "You have " + beg + " \"" + selectedTest.getName() + "\"! If you " + cont + " - click \"Yes\". If you don't want to take the test - click \"No\"", "Yes", "No");
    }

    private void setQuestion(int countAnswer, String label, String... answers) {
        init();
        setNumberQuestions(countAnswer);
        this.label.setText(label);
        for (int i = 0; i < answers.length; i++) {
            buttons[i].setText(answers[i]);
        }
    }

    private void setQuestion(int countAnswer, String label, ArrayList<String> answers) {
        init();
        setNumberQuestions(countAnswer);
        if (label.equals("")) {
            this.label.setStyle("-fx-text-fill: red;");
            this.label.setText("is empty");
        } else {
            this.label.setStyle("-fx-text-fill: white;");
            this.label.setText(label);
        }
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).equals("")) {
                buttons[i].setStyle("-fx-text-fill: red;");
                buttons[i].setText("is empty");
            } else {
                buttons[i].setStyle("-fx-text-fill: black;");
                buttons[i].setText(answers.get(i));
            }
        }
    }

    private void init() {
        label.setStyle("-fx-text-fill: white;");
        buttons[0] = button1;
        buttons[1] = button2;
        buttons[2] = button3;
        buttons[3] = button4;
        buttons[4] = button5;
        buttons[5] = button6;
        buttons[6] = button7;
        buttons[7] = button8;
        buttons[8] = button9;
        for (Button button : buttons) {
            setStyle(button);
        }
    }

    private void setStyle(Button button) {
        button.setStyle("-fx-text-fill: black;");
    }

    public void createQuestions() throws Exception {
        Stage popup = new Stage();
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.WINDOW_MODAL);
        popup.setResizable(false);
        popup.setTitle("Create your test!");
        popup.initOwner(label.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/paneСreateQuestions.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 463, 385);
        loader.<ControllerCreateQuestions>getController().initData(this);
        popup.setScene(scene);
        popup.show();
    }

    public void findTests() throws Exception {
        Stage popup = new Stage();
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.WINDOW_MODAL);
        popup.setResizable(false);
        popup.setTitle("Сhoose one of the tests");
        popup.initOwner(label.getScene().getWindow());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/paneSelection.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root, 700, 500);
        loader.<ControllerSelection>getController().initData(this);
        popup.setScene(scene);
        popup.show();
    }

    private void setNumberQuestions(int numberAnswers) {
        switch (numberAnswers) {
            case 2:
                defaultPositions3();
                button3.setVisible(false);
                button4.setVisible(false);
                setProperties(button1, 14, 156, 93.5, 435);
                setProperties(button2, 14, 277, 93.5, 435);
                break;
            case 3:
                defaultPositions3();
                button4.setVisible(false);
                setProperties(button3, 129, 277, 93.5, 204.5);
                break;
            case 4:
                defaultPositions3();
                break;
            case 5:
                defaultPositions2();
                button6.setVisible(false);
                setProperties(button4, 89, 277, 93.5, 133);
                setProperties(button5, 241, 277, 93.5, 133);
                break;
            case 6:
                defaultPositions2();
                break;
            case 7:
                defaultPositions1();
                button8.setVisible(false);
                button9.setVisible(false);
                button7.setLayoutX(165);
                break;
            case 8:
                defaultPositions1();
                button9.setVisible(false);
                button8.setLayoutX(241);
                button7.setLayoutX(89);
                break;
            case 9:
                defaultPositions1();
                break;
        }
    }

    private void setProperties(Button button, boolean vis, int X, int Y, double prefH, double prefW) {
        button.setLayoutX(X);
        button.setLayoutY(Y);
        button.setPrefHeight(prefH);
        button.setPrefWidth(prefW);
        button.setVisible(vis);
    }

    private void setProperties(Button button, int X, int Y, double prefH, double prefW) {
        button.setLayoutX(X);
        button.setLayoutY(Y);
        button.setPrefHeight(prefH);
        button.setPrefWidth(prefW);
    }

    private void defaultPositions3() {
        defaultPositions2();
        button5.setVisible(false);
        button6.setVisible(false);
        setProperties(button1, 14, 156, 93.5, 204.5);
        setProperties(button2, 244, 156, 93.5, 204.5);
        setProperties(button3, 14, 277, 93.5, 204.5);
        setProperties(button4, 244, 277, 93.5, 204.5);
    }

    private void defaultPositions2() {
        defaultPositions1();
        button7.setVisible(false);
        button8.setVisible(false);
        button9.setVisible(false);
        setProperties(button1, 14, 156, 93.5, 133);
        setProperties(button2, 165, 156, 93.5, 133);
        setProperties(button3, 316, 156, 93.5, 133);
        setProperties(button4, 14, 277, 93.5, 133);
        setProperties(button5, 165, 277, 93.5, 133);
        setProperties(button6, 316, 277, 93.5, 133);
    }

    private void defaultPositions1() {
        setProperties(button1, true, 14, 156, 53, 133);
        setProperties(button2, true, 165, 156, 53, 133);
        setProperties(button3, true, 316, 156, 53, 133);
        setProperties(button4, true, 14, 237, 53, 133);
        setProperties(button5, true, 165, 237, 53, 133);
        setProperties(button6, true, 316, 237, 53, 133);
        setProperties(button7, true, 14, 318, 53, 133);
        setProperties(button8, true, 165, 318, 53, 133);
        setProperties(button9, true, 316, 318, 53, 133);
    }

    public void exit() throws Exception {
        Stage popup = new Stage();
        popup.initStyle(StageStyle.UNDECORATED);
        popup.setAlwaysOnTop(true);
        popup.initModality(Modality.WINDOW_MODAL);
        popup.initOwner(label.getScene().getWindow());
        Parent root = FXMLLoader.load(getClass().getResource("../view/paneExit.fxml"));
        Scene scene = new Scene(root, 365, 179);
        popup.setScene(scene);
        popup.show();
    }
}
