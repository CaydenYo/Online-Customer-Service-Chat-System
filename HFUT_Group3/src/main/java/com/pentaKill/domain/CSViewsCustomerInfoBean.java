package com.pentaKill.domain;

public class CSViewsCustomerInfoBean {

    private String customer_name;
    private int customer_age;
    private String customer_address;
    private int customer_sex;
    private String customer_nickname;
    private String customer_img;
    private String customer_email;
    private String current_time;

    public CSViewsCustomerInfoBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CSViewsCustomerInfoBean(String customer_name, int customer_age, String customer_address, int customer_sex,
            String customer_nickname, String customer_img, String customer_email, String current_time) {
        super();
        this.customer_name = customer_name;
        this.customer_age = customer_age;
        this.customer_address = customer_address;
        this.customer_sex = customer_sex;
        this.customer_nickname = customer_nickname;
        this.customer_img = customer_img;
        this.customer_email = customer_email;
        this.current_time = current_time;
    }

    @Override
    public String toString() {
        return "CSViewsCustomerInfoBean [customer_name=" + customer_name + ", customer_age=" + customer_age
                + ", customer_address=" + customer_address + ", customer_sex=" + customer_sex + ", customer_nickname="
                + customer_nickname + ", customer_img=" + customer_img + ", customer_email=" + customer_email
                + ", current_time=" + current_time + "]";
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(String current_time) {
        this.current_time = current_time;
    }

    public int getCustomer_sex() {
        return customer_sex;
    }

    public void setCustomer_sex(int customer_sex) {
        this.customer_sex = customer_sex;
    }

    public String getCustomer_nickname() {
        return customer_nickname;
    }

    public void setCustomer_nickname(String customer_nickname) {
        this.customer_nickname = customer_nickname;
    }

    public String getCustomer_img() {
        return customer_img;
    }

    public void setCustomer_img(String customer_img) {
        this.customer_img = customer_img;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public int getCustomer_age() {
        return customer_age;
    }

    public void setCustomer_age(int customer_age) {
        this.customer_age = customer_age;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

}
