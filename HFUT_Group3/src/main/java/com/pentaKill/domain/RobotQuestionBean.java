package com.pentaKill.domain;

public class RobotQuestionBean {

    private String question;
    private String anwser;

    public RobotQuestionBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RobotQuestionBean(String question, String anwser) {
        super();
        this.question = question;
        this.anwser = anwser;
    }

    @Override
    public String toString() {
        return "RobotQuestionBean [question=" + question + ", anwser=" + anwser + "]";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnwser() {
        return anwser;
    }

    public void setAnwser(String anwser) {
        this.anwser = anwser;
    }

}
