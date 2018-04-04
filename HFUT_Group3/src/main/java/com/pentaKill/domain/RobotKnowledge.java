package com.pentaKill.domain;

public class RobotKnowledge {

    int knowledge_id;
    int robot_id;
    String question;
    String answer;

    public RobotKnowledge() {
        super();
        // TODO Auto-generated constructor stub
    }

    public RobotKnowledge(int knowledge_id, int robot_id, String question, String answer) {
        super();
        this.knowledge_id = knowledge_id;
        this.robot_id = robot_id;
        this.question = question;
        this.answer = answer;
    }

    public int getKnowledge_id() {
        return knowledge_id;
    }

    public void setKnowledge_id(int knowledge_id) {
        this.knowledge_id = knowledge_id;
    }

    public int getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(int robot_id) {
        this.robot_id = robot_id;
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
