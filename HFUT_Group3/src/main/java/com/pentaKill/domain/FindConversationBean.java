package com.pentaKill.domain;

public class FindConversationBean {
    private int customer_id;
    private int cs_id;

    public FindConversationBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FindConversationBean(int customer_id, int cs_id) {
        super();
        this.customer_id = customer_id;
        this.cs_id = cs_id;
    }

    @Override
    public String toString() {
        return "FindConversationBean [customer_id=" + customer_id + ", cs_id=" + cs_id + "]";
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

}
