package com.pentaKill.domain;

public class CSManager {

    private String csm_email;
    private String csm_pwd;
    private int company_id;

    public CSManager(String csm_email, String csm_pwd, int company_id) {
        super();
        this.csm_email = csm_email;
        this.csm_pwd = csm_pwd;
        this.company_id = company_id;
    }

    public CSManager() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getCsm_email() {
        return csm_email;
    }

    public void setCsm_email(String csm_email) {
        this.csm_email = csm_email;
    }

    public String getCsm_pwd() {
        return csm_pwd;
    }

    public void setCsm_pwd(String csm_pwd) {
        this.csm_pwd = csm_pwd;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

}
