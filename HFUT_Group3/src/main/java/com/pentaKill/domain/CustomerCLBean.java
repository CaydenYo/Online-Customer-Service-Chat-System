package com.pentaKill.domain;

public class CustomerCLBean {
    private int customer_id;
    private String customer_nickname;
    private String customer_img;

    public CustomerCLBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CustomerCLBean(int customer_id, String customer_nickname, String customer_img) {
        super();
        this.customer_id = customer_id;
        this.customer_nickname = customer_nickname;
        this.customer_img = customer_img;
    }

    @Override
    public String toString() {
        return "CustomerCLBean [customer_id=" + customer_id + ", customer_nickname=" + customer_nickname
                + ", customer_img=" + customer_img + "]";
    }

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

    public String getCustomer_img() {
        return customer_img;
    }

    public void setCustomer_img(String customer_img) {
        this.customer_img = customer_img;
    }

}
