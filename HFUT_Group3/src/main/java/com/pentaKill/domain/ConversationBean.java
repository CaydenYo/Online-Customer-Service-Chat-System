package com.pentaKill.domain;

import java.sql.Timestamp;

public class ConversationBean {
	
	private int customer_id;
	private int cs_id;
	private Timestamp start_time;
	private Timestamp end_time;
	private int score;

	public ConversationBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ConversationBean(int customer_id, int cs_id, Timestamp start_time, Timestamp end_time, int score) {
		super();
		this.customer_id = customer_id;
		this.cs_id = cs_id;
		this.start_time = start_time;
		this.end_time = end_time;
		this.score = score;
	}

	@Override
	public String toString() {
		return "ConversationBean [customer_id=" + customer_id + ", cs_id=" + cs_id + ", start_time=" + start_time
				+ ", end_time=" + end_time + ", score=" + score + "]";
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCs_id() {
		return cs_id;
	}

	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}

	public Timestamp getStart_time() {
		return start_time;
	}

	public void setStart_time(Timestamp start_time) {
		this.start_time = start_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
