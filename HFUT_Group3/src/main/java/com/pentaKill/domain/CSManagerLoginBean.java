package com.pentaKill.domain;

public class CSManagerLoginBean {
    private String csm_email;
    private String csm_pwd;

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

    public CSManagerLoginBean(String csm_email, String csm_pwd) {
        super();
        this.csm_email = csm_email;
        this.csm_pwd = csm_pwd;
    }

}
