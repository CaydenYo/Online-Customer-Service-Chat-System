package com.pentaKill.domain;

public class WaitingCustomerBean {

    private int customer_int;
    private String customer_nickname;
    private String customer_img;

    public WaitingCustomerBean(int customer_int, String customer_nickname, String customer_img) {
        super();
        this.customer_int = customer_int;
        this.customer_nickname = customer_nickname;
        this.customer_img = customer_img;
    }

    public WaitingCustomerBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCustomer_int() {
        return customer_int;
    }

    public void setCustomer_int(int customer_int) {
        this.customer_int = customer_int;
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

}
