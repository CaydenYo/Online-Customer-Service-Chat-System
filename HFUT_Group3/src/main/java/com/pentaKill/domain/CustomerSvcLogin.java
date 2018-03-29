package com.pentaKill.domain;

import org.hibernate.validator.constraints.NotBlank;

public class CustomerSvcLogin {
	@NotBlank
	private String cs_email;
	@NotBlank
	private String cs_pwd;

	public String getCs_email() {
		return cs_email;
	}

	public void setCs_email(String cs_email) {
		this.cs_email = cs_email;
	}

	public String getCs_pwd() {
		return cs_pwd;
	}

	public void setCs_pwd(String cs_pwd) {
		this.cs_pwd = cs_pwd;
	}

}
