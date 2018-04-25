package com.pentaKill.domain;

public class CustomerLoginBean {
    int customer_id;
    String customer_nickname;
    String customer_name;
    int customer_status;
    int customer_age;
    int customer_sex;
    String customer_email;
    String customer_pwd;
    String customer_address;
    String customer_img;

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_nickname() {
        return customer_nickname;
    }

    public void setCustomer_nickname(String customer_nickname) {
        this.customer_nickname = customer_nickname;
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

    public int getCustomer_age() {
        return customer_age;
    }

    public void setCustomer_age(int customer_age) {
        this.customer_age = customer_age;
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

    public CustomerLoginBean(int customer_id, String customer_nickname, String customer_name, int customer_status,
            int customer_age, int customer_sex, String customer_email, String customer_pwd, String customer_address,
            String customer_img) {
        super();
        this.customer_id = customer_id;
        this.customer_nickname = customer_nickname;
        this.customer_name = customer_name;
        this.customer_status = customer_status;
        this.customer_age = customer_age;
        this.customer_sex = customer_sex;
        this.customer_email = customer_email;
        this.customer_pwd = customer_pwd;
        this.customer_address = customer_address;
        this.customer_img = customer_img;
    }

    public CustomerLoginBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "CustomerLoginBean [customer_id=" + customer_id + ", customer_nickname=" + customer_nickname
                + ", customer_name=" + customer_name + ", customer_status=" + customer_status + ", customer_age="
                + customer_age + ", customer_sex=" + customer_sex + ", customer_email=" + customer_email
                + ", customer_pwd=" + customer_pwd + ", customer_address=" + customer_address + ", customer_img="
                + customer_img + "]";
    }

}
