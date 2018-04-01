package com.pentaKill.domain;

import java.sql.Timestamp;

public class LastCustomerServiceBean {
    private int cs_id;
    private Timestamp start_time;

    public LastCustomerServiceBean(int cs_id, Timestamp start_time) {
        super();
        this.cs_id = cs_id;
        this.start_time = start_time;
    }

    public LastCustomerServiceBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

}
