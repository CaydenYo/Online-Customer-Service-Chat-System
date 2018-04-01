package com.pentaKill.domain;

public class ChooseCustomerServiceBean {
    private int cs_id;
    private int cs_status;
    private String cs_workId;
    private int cs_operating_number;
    private int cs_waiting_number;
    private int cs_operated_number;
    private int cs_waited_number;

    public ChooseCustomerServiceBean(int cs_id, int cs_status, String cs_workId, int cs_operating_number,
            int cs_waiting_number, int cs_operated_number, int cs_waited_number) {
        super();
        this.cs_id = cs_id;
        this.cs_status = cs_status;
        this.cs_workId = cs_workId;
        this.cs_operating_number = cs_operating_number;
        this.cs_waiting_number = cs_waiting_number;
        this.cs_operated_number = cs_operated_number;
        this.cs_waited_number = cs_waited_number;
    }

    public ChooseCustomerServiceBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public int getCs_status() {
        return cs_status;
    }

    public void setCs_status(int cs_status) {
        this.cs_status = cs_status;
    }

    public String getCs_workId() {
        return cs_workId;
    }

    public void setCs_workId(String cs_workId) {
        this.cs_workId = cs_workId;
    }

    public int getCs_operating_number() {
        return cs_operating_number;
    }

    public void setCs_operating_number(int cs_operating_number) {
        this.cs_operating_number = cs_operating_number;
    }

    public int getCs_waiting_number() {
        return cs_waiting_number;
    }

    public void setCs_waiting_number(int cs_waiting_number) {
        this.cs_waiting_number = cs_waiting_number;
    }

    public int getCs_operated_number() {
        return cs_operated_number;
    }

    public void setCs_operated_number(int cs_operated_number) {
        this.cs_operated_number = cs_operated_number;
    }

    public int getCs_waited_number() {
        return cs_waited_number;
    }

    public void setCs_waited_number(int cs_waited_number) {
        this.cs_waited_number = cs_waited_number;
    }

}
