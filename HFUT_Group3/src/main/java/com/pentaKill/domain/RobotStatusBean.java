package com.pentaKill.domain;

public class RobotStatusBean {

    private int company_id;
    private int robot_open_flag;

    public RobotStatusBean(int company_id, int robot_open_flag) {
        super();
        this.company_id = company_id;
        this.robot_open_flag = robot_open_flag;
    }

    public RobotStatusBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getRobot_open_flag() {
        return robot_open_flag;
    }

    public void setRobot_open_flag(int robot_open_flag) {
        this.robot_open_flag = robot_open_flag;
    }

}
