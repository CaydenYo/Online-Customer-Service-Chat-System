package com.pentaKill.domain;

public class CustomerInfoBean {
	
	private String customer_name;
	private int customer_status;
	private int customer_sex;
	private String customer_email;
	private String customer_pwd;
	private String customer_address;
	private String customer_img;

	public CustomerInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerInfoBean(String customer_name, int customer_status, int customer_sex, String customer_email,
			String customer_pwd, String customer_address, String customer_img) {
		super();
		this.customer_name = customer_name;
		this.customer_status = customer_status;
		this.customer_sex = customer_sex;
		this.customer_email = customer_email;
		this.customer_pwd = customer_pwd;
		this.customer_address = customer_address;
		this.customer_img = customer_img;
	}

	@Override
	public String toString() {
		return "CustomerInfoBean [customer_name=" + customer_name + ", customer_status=" + customer_status
				+ ", customer_sex=" + customer_sex + ", customer_email=" + customer_email + ", customer_pwd="
				+ customer_pwd + ", customer_address=" + customer_address + ", customer_img=" + customer_img + "]";
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public int getCustomer_status() {
		return customer_status;
	}

	public void setCustomer_status(int customer_status) {
		this.customer_status = customer_status;
	}

	public int getCustomer_sex() {
		return customer_sex;
	}

	public void setCustomer_sex(int customer_sex) {
		this.customer_sex = customer_sex;
	}

	public String getCustomer_email() {
		return customer_email;
	}

	public void setCustomer_email(String customer_email) {
		this.customer_email = customer_email;
	}

	public String getCustomer_pwd() {
		return customer_pwd;
	}

	public void setCustomer_pwd(String customer_pwd) {
		this.customer_pwd = customer_pwd;
	}

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public String getCustomer_img() {
		return customer_img;
	}

	public void setCustomer_img(String customer_img) {
		this.customer_img = customer_img;
	}

}
