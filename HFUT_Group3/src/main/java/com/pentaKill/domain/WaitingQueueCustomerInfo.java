package com.pentaKill.domain;

public class WaitingQueueCustomerInfo {
    int customer_id;
    String customer_nickname;
    String customer_img;

    public WaitingQueueCustomerInfo(int customer_id, String customer_nickname, String customer_img) {
        super();
        this.customer_id = customer_id;
        this.customer_nickname = customer_nickname;
        this.customer_img = customer_img;
    }

    public WaitingQueueCustomerInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "WaitingQueueCustomerInfo [customer_id=" + customer_id + ", customer_nickname=" + customer_nickname
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
