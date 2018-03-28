package com.pentaKill.domain;


public class CustomerService {
	
	private int cs_id;
	private String cs_workId;
	private String cs_name;
	private String cs_nickName;
	private String cs_pwd;
	private String cs_email;
	private String cs_img;
	private int cs_greetings;
	private int cs_status;
	private int cs_operating_number;
	private int cs_waiting_number;
	private float cs_score;
	
	public CustomerService(int cs_id, String cs_workId, String cs_name, String cs_nickName, String cs_pwd,
			String cs_email, String cs_img, int cs_greetings, int cs_status, int cs_operating_number,
			int cs_waiting_number, float cs_score) {
		super();
		this.cs_id = cs_id;
		this.cs_workId = cs_workId;
		this.cs_name = cs_name;
		this.cs_nickName = cs_nickName;
		this.cs_pwd = cs_pwd;
		this.cs_email = cs_email;
		this.cs_img = cs_img;
		this.cs_greetings = cs_greetings;
		this.cs_status = cs_status;
		this.cs_operating_number = cs_operating_number;
		this.cs_waiting_number = cs_waiting_number;
		this.cs_score = cs_score;
	}
	
	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "CustomerService [cs_id=" + cs_id + ", cs_workId=" + cs_workId + ", cs_name=" + cs_name
				+ ", cs_nickName=" + cs_nickName + ", cs_pwd=" + cs_pwd + ", cs_email=" + cs_email + ", cs_img="
				+ cs_img + ", cs_greetings=" + cs_greetings + ", cs_status=" + cs_status + ", cs_operating_number="
				+ cs_operating_number + ", cs_waiting_number=" + cs_waiting_number + ", cs_score=" + cs_score + "]";
	}
	
	public int getCs_id() {
		return cs_id;
	}
	
	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}
	
	public String getCs_workId() {
		return cs_workId;
	}

	public void setCs_workId(String cs_workId) {
		this.cs_workId = cs_workId;
	}

	public String getCs_name() {
		return cs_name;
	}

	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}

	public String getCs_nickName() {
		return cs_nickName;
	}

	public void setCs_nickName(String cs_nickName) {
		this.cs_nickName = cs_nickName;
	}

	public String getCs_pwd() {
		return cs_pwd;
	}

	public void setCs_pwd(String cs_pwd) {
		this.cs_pwd = cs_pwd;
	}

	public String getCs_email() {
		return cs_email;
	}

	public void setCs_email(String cs_email) {
		this.cs_email = cs_email;
	}

	public String getCs_img() {
		return cs_img;
	}

	public void setCs_img(String cs_img) {
		this.cs_img = cs_img;
	}

	public int getCs_greetings() {
		return cs_greetings;
	}

	public void setCs_greetings(int cs_greetings) {
		this.cs_greetings = cs_greetings;
	}

	public int getCs_status() {
		return cs_status;
	}

	public void setCs_status(int cs_status) {
		this.cs_status = cs_status;
	}

	public int getCs_operating_number() {
		return cs_operating_number;
	}

	public void setCs_operating_number(int cs_operating_number) {
		this.cs_operating_number = cs_operating_number;
	}

	public int getCs_waiting_number() {
		return cs_waiting_number;
	}

	public void setCs_waiting_number(int cs_waiting_number) {
		this.cs_waiting_number = cs_waiting_number;
	}

	public float getCs_score() {
		return cs_score;
	}

	public void setCs_score(float cs_score) {
		this.cs_score = cs_score;
	}
	
	
}

