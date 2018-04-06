package com.pentaKill.domain;

public class ListCSInfoBean {
    private String cs_workId;
    private String cs_name;
    private String cs_nickName;
    private int cs_register_status;
    private int cs_status;
    private String cs_email;
    private float cs_score;

    public ListCSInfoBean(String cs_workId, String cs_name, String cs_nickName, int cs_register_status, int cs_status,
            String cs_email, float cs_score) {
        super();
        this.cs_workId = cs_workId;
        this.cs_name = cs_name;
        this.cs_nickName = cs_nickName;
        this.cs_register_status = cs_register_status;
        this.cs_status = cs_status;
        this.cs_email = cs_email;
        this.cs_score = cs_score;
    }

    public ListCSInfoBean() {
        super();
        // TODO Auto-generated constructor stub
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

    public String getCs_nickName() {
        return cs_nickName;
    }

    public void setCs_nickName(String cs_nickName) {
        this.cs_nickName = cs_nickName;
    }

    public int getCs_register_status() {
        return cs_register_status;
    }

    public void setCs_register_status(int cs_register_status) {
        this.cs_register_status = cs_register_status;
    }

    public int getCs_status() {
        return cs_status;
    }

    public void setCs_status(int cs_status) {
        this.cs_status = cs_status;
    }

    public String getCs_email() {
        return cs_email;
    }

    public void setCs_email(String cs_email) {
        this.cs_email = cs_email;
    }

    public float getCs_score() {
        return cs_score;
    }

    public void setCs_score(float cs_score) {
        this.cs_score = cs_score;
    }

}
