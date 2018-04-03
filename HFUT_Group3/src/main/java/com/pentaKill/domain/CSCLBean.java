package com.pentaKill.domain;

public class CSCLBean {
    private int cs_id;
    // 这个名字这里很容易出错!!!!!!
    private String cs_nickName;
    private String cs_img;

    public CSCLBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CSCLBean(int cs_id, String cs_nickName, String cs_img) {
        super();
        this.cs_id = cs_id;
        this.cs_nickName = cs_nickName;
        this.cs_img = cs_img;
    }

    @Override
    public String toString() {
        return "CSCLBean [cs_id=" + cs_id + ", cs_nickName=" + cs_nickName + ", cs_img=" + cs_img + "]";
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public String getCs_nickName() {
        return cs_nickName;
    }

    public void setCs_nickName(String cs_nickName) {
        this.cs_nickName = cs_nickName;
    }

    public String getCs_img() {
        return cs_img;
    }

    public void setCs_img(String cs_img) {
        this.cs_img = cs_img;
    }

}
