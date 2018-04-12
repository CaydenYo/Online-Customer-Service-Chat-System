package com.pentaKill.domain;

public class CustomerServiceStatusBean {

    private int cs_id;
    private String cs_img;
    private int cs_status;
    private String cs_workId;
    private String cs_name;
    private int cs_waited_number; // 正在排队的人数
    private int cs_operated_number;// 正在操作的人数
    private float cs_score;

    public CustomerServiceStatusBean(int cs_id, String cs_img, int cs_status, String cs_workId, String cs_name,
            int cs_waited_number, int cs_operated_number, float cs_score) {
        super();
        this.cs_id = cs_id;
        this.cs_img = cs_img;
        this.cs_status = cs_status;
        this.cs_workId = cs_workId;
        this.cs_name = cs_name;
        this.cs_waited_number = cs_waited_number;
        this.cs_operated_number = cs_operated_number;
        this.cs_score = cs_score;
    }

    public CustomerServiceStatusBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public String getCs_img() {
        return cs_img;
    }

    public void setCs_img(String cs_img) {
        this.cs_img = cs_img;
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

    public String getCs_name() {
        return cs_name;
    }

    public void setCs_name(String cs_name) {
        this.cs_name = cs_name;
    }

    public int getCs_waited_number() {
        return cs_waited_number;
    }

    public void setCs_waited_number(int cs_waited_number) {
        this.cs_waited_number = cs_waited_number;
    }

    public int getCs_operated_number() {
        return cs_operated_number;
    }

    public void setCs_operated_number(int cs_operated_number) {
        this.cs_operated_number = cs_operated_number;
    }

    public float getCs_score() {
        return cs_score;
    }

    public void setCs_score(float cs_score) {
        this.cs_score = cs_score;
    }

    @Override
    public String toString() {
        return "CustomerServiceStatusBean [cs_id=" + cs_id + ", cs_img=" + cs_img + ", cs_status=" + cs_status
                + ", cs_workId=" + cs_workId + ", cs_name=" + cs_name + ", cs_waited_number=" + cs_waited_number
                + ", cs_operated_number=" + cs_operated_number + ", cs_score=" + cs_score + "]";
    }

}
