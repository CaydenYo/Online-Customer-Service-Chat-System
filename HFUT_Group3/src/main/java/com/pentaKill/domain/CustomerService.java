package com.pentaKill.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

public class CustomerService {

	private int cs_id;
	private String company_id;
	private String cs_workId;
	@NotBlank(message = "用户名不能为空")
	private String cs_name;
	private String cs_nickName;
	@NotBlank(message = "密码不能为空")
	private String cs_pwd;
	@NotBlank(message = "邮箱不能为空")
	private String cs_email;
	private String cs_img;
	private int cs_greetings;
	private int cs_status;
	@Range(min = 0, max = 99, message = "请选择可同时接待人数")
	private int cs_operating_number;
	@Range(min = 0, max = 99, message = "请选择等待接待人数")
	private int cs_waiting_number;
	private float cs_score;
	private int cs_register_id;
	private String cs_code;

	
	
	public CustomerService(int cs_id, String company_id, String cs_workId, String cs_name, String cs_nickName,
			String cs_pwd, String cs_email, String cs_img, int cs_greetings, int cs_status, int cs_operating_number,
			int cs_waiting_number, float cs_score, int cs_register_id, String cs_code) {
		super();
		this.cs_id = cs_id;
		this.company_id = company_id;
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
		this.cs_register_id = cs_register_id;
		this.cs_code = cs_code;
	}



	public CustomerService() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "CustomerService [cs_id=" + cs_id + ", company_id=" + company_id + ", cs_workId=" + cs_workId
				+ ", cs_name=" + cs_name + ", cs_nickName=" + cs_nickName + ", cs_pwd=" + cs_pwd + ", cs_email="
				+ cs_email + ", cs_img=" + cs_img + ", cs_greetings=" + cs_greetings + ", cs_status=" + cs_status
				+ ", cs_operating_number=" + cs_operating_number + ", cs_waiting_number=" + cs_waiting_number
				+ ", cs_score=" + cs_score + ", cs_register_id=" + cs_register_id + ", cs_code=" + cs_code + "]";
	}



	public int getCs_register_id() {
		return cs_register_id;
	}



	public void setCs_register_id(int cs_register_id) {
		this.cs_register_id = cs_register_id;
	}



	public String getCs_code() {
		return cs_code;
	}



	public void setCs_code(String cs_code) {
		this.cs_code = cs_code;
	}



	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
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
