package com.pentaKill.domain;

public class UpdateKnowledgeBean {

	int knowledge_id;
	String question;
	String answer;

	public UpdateKnowledgeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateKnowledgeBean(int knowledge_id, String question, String answer) {
		super();
		this.knowledge_id = knowledge_id;
		this.question = question;
		this.answer = answer;
	}

	public int getKnowledge_id() {
		return knowledge_id;
	}

	public void setKnowledge_id(int knowledge_id) {
		this.knowledge_id = knowledge_id;
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
