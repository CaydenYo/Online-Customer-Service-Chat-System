package com.pentaKill.domain;

public class CSManager {
	
	private int csm_id;
	private String csm_email;
	private String csm_pwd;
	private int company_id;
	public int getCsm_id() {
		return csm_id;
	}
	public void setCsm_id(int csm_id) {
		this.csm_id = csm_id;
	}
	public String getCsm_email() {
		return csm_email;
	}
	public void setCsm_email(String csm_email) {
		this.csm_email = csm_email;
	}
	public String getCsm_pwd() {
		return csm_pwd;
	}
	public void setCsm_pwd(String csm_pwd) {
		this.csm_pwd = csm_pwd;
	}
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	
}
