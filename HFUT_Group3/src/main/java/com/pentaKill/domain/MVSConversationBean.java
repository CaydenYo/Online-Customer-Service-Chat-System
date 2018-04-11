package com.pentaKill.domain;

import java.sql.Timestamp;

public class MVSConversationBean {
    private int conversation_id;
    private int customer_id;
    private String customer_nickname;
    private int cs_id;
    private String cs_nickName;
    private Timestamp start_time;
    private Timestamp end_time;
    private int score;

    public MVSConversationBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public MVSConversationBean(int conversation_id, int customer_id, String customer_nickname, int cs_id,
            String cs_nickName, Timestamp start_time, Timestamp end_time, int score) {
        super();
        this.conversation_id = conversation_id;
        this.customer_id = customer_id;
        this.customer_nickname = customer_nickname;
        this.cs_id = cs_id;
        this.cs_nickName = cs_nickName;
        this.start_time = start_time;
        this.end_time = end_time;
        this.score = score;
    }

    @Override
    public String toString() {
        return "MVSConversationBean [conversation_id=" + conversation_id + ", customer_id=" + customer_id
                + ", customer_nickname=" + customer_nickname + ", cs_id=" + cs_id + ", cs_nickName=" + cs_nickName
                + ", start_time=" + start_time + ", end_time=" + end_time + ", score=" + score + "]";
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_nickname() {
        return customer_nickname;
    }

    public void setCustomer_nickname(String customer_nickname) {
        this.customer_nickname = customer_nickname;
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

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
