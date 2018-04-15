package com.pentaKill.domain;

public class RobotQuestionBean {

    private String question;
    private String answer;

    public RobotQuestionBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RobotQuestionBean(String question, String answer) {
        super();
        this.question = question;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "RobotQuestionBean [question=" + question + ", answer=" + answer + "]";
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
