package com.pentaKill.domain;

public class CustomerServiceRegisterBean {
	private String cs_email;
	private String cs_workId;
	private String company_id;
	private int cs_register_status;
	private String cs_code;
	
	
	
	public String getCs_workId() {
		return cs_workId;
	}
	public void setCs_workId(String cs_workId) {
		this.cs_workId = cs_workId;
	}
	public String getCs_email() {
		return cs_email;
	}
	public void setCs_email(String cs_email) {
		this.cs_email = cs_email;
	}
	public String getCompany_id() {
		return company_id;
	}
	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}
	
	public int getCs_register_status() {
        return cs_register_status;
    }
    public void setCs_register_status(int cs_register_status) {
        this.cs_register_status = cs_register_status;
    }
    public String getCs_code() {
		return cs_code;
	}
	public void setCs_code(String cs_code) {
		this.cs_code = cs_code;
	}
	
	
}
