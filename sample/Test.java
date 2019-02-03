package sample;

import java.util.ArrayList;

public class Test {
    private ArrayList<Question> questions = new ArrayList<>();
    private Question question;
    private String name;
    private int views;
    private int countQuestions;
    private long id;
    private Double rating;

    public long getId() {
        return id;
    }

    void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public int getViews() {
        return views;
    }

    public int getCountQuestions() {
        return countQuestions;
    }

    public Double getRating() {
        return rating;
    }

    public Test() {
    }

    public Test(String name, int views, double rating) {
        this.name = name;
        this.views = views;
        this.rating = rating;
    }

    void addQuestion(Question question) {
        questions.add(question);
    }

    void setCountQuestions(int countQuestions) {
        this.countQuestions = countQuestions;
    }

    void setRating(Double rating) {
        this.rating = rating;
    }

    void setViews(int views) {
        this.views = views;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void removeLastQuestion() {
        questions.remove(questions.size() - 1);
    }

    public void createQuetions() {
        Question question = new Question();
        this.question = question;
        questions.add(question);
    }

    public void setQuestion(String question) {
        this.question.setQuestion(question);
    }

    public void setNumberAnswerChoices(int numberAnswerChoices) {
        this.question.setNumberAnswerChoices(numberAnswerChoices);
    }

    public void setAnswers(String answer) {
        this.question.setAnswers(answer);
    }

    public void clearQuestions() {
        this.questions.clear();
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }
}