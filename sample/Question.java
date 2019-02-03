package sample;

import java.util.ArrayList;

public class Question {
    private ArrayList<String> AnswerChoices = new ArrayList<>(2);
    private int numberAnswerChoices;
    private String question;
    private long id;

    long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    public ArrayList<String> getAnswerChoices() {
        return AnswerChoices;
    }

    public String getQuestion() {
        return question;
    }

    void setNumberAnswerChoices(int numberAnswerChoices) {
        this.numberAnswerChoices = numberAnswerChoices;
    }

    public int getNumberAnswerChoices() {
        return numberAnswerChoices;
    }

    void setQuestion(String question) {
        this.question = question;
    }

    void setAnswers(String answer) {
        AnswerChoices.add(answer);
    }
}
