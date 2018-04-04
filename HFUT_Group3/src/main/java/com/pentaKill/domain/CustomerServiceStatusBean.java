package com.pentaKill.domain;

public class CustomerServiceStatusBean {

    private int cs_id;
    private String cs_workId;
    private String cs_name;
    private int cs_status;
    private int cs_operating_number;
    private String cs_img;
    private float cs_score;

    public CustomerServiceStatusBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CustomerServiceStatusBean(int cs_id, String cs_workId, String cs_name, int cs_status,
            int cs_operating_number, String cs_img, float cs_score) {
        super();
        this.cs_id = cs_id;
        this.cs_workId = cs_workId;
        this.cs_name = cs_name;
        this.cs_status = cs_status;
        this.cs_operating_number = cs_operating_number;
        this.cs_img = cs_img;
        this.cs_score = cs_score;
    }

    @Override
    public String toString() {
        return "CustomerServiceStatusBean [cs_id=" + cs_id + ", cs_workId=" + cs_workId + ", cs_name=" + cs_name
                + ", cs_status=" + cs_status + ", cs_operating_number=" + cs_operating_number + ", cs_img=" + cs_img
                + ", cs_score=" + cs_score + "]";
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public String getCs_workId() {
        return cs_workId;
    }

    public void setCs_workId(String cs_workId) {
        this.cs_workId = cs_workId;
    }

    public String getCs_name() {
        return cs_name;
    }

    public void setCs_name(String cs_name) {
        this.cs_name = cs_name;
    }

    public int getCs_status() {
        return cs_status;
    }

    public void setCs_status(int cs_status) {
        this.cs_status = cs_status;
    }

    public int getCs_operating_number() {
        return cs_operating_number;
    }

    public void setCs_operating_number(int cs_operating_number) {
        this.cs_operating_number = cs_operating_number;
    }

    public String getCs_img() {
        return cs_img;
    }

    public void setCs_img(String cs_img) {
        this.cs_img = cs_img;
    }

    public float getCs_score() {
        return cs_score;
    }

    public void setCs_score(float cs_score) {
        this.cs_score = cs_score;
    }

}
