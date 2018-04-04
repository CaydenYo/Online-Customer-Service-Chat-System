package com.pentaKill.domain;

public class Company {
    private int company_id;
    private String company_name;
    private int robot_id;
    private String robot_name;
    private int dstribution_type;
    private int mininum_operating_num;
    private int customer_info_flag;

    public int getMininum_operating_num() {
        return mininum_operating_num;
    }

    public void setMininum_operating_num(int mininum_operating_num) {
        this.mininum_operating_num = mininum_operating_num;
    }

    public int getCustomer_info_flag() {
        return customer_info_flag;
    }

    public void setCustomer_info_flag(int customer_info_flag) {
        this.customer_info_flag = customer_info_flag;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public int getDstribution_type() {
        return dstribution_type;
    }

    public void setDstribution_type(int dstribution_type) {
        this.dstribution_type = dstribution_type;
    }

    public int getRobot_id() {
        return robot_id;
    }

    public void setRobot_id(int robot_id) {
        this.robot_id = robot_id;
    }

    public String getRobot_name() {
        return robot_name;
    }

    public void setRobot_name(String robot_name) {
        this.robot_name = robot_name;
    }

}
