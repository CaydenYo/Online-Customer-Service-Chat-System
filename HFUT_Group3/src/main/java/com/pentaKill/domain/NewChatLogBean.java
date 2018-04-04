package com.pentaKill.domain;

import java.sql.Timestamp;

public class NewChatLogBean implements Comparable<NewChatLogBean> {
    private int conversation_id;
    private int receiver_id;
    private int sender_id;
    private int from_customer;
    private Timestamp time;
    private int content_type;
    private String content;
    // 仅记录发送者的昵称和头像
    private String sender_nickname;
    private String sender_img;

    // 按照时间对chatlog进行排序
    @Override
    public int compareTo(NewChatLogBean o) {
        int flag = this.time.compareTo(o.time);
        return flag;
    }

    public NewChatLogBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public NewChatLogBean(int conversation_id, int receiver_id, int sender_id, int from_customer, Timestamp time,
            int content_type, String content, String sender_nickname, String sender_img) {
        super();
        this.conversation_id = conversation_id;
        this.receiver_id = receiver_id;
        this.sender_id = sender_id;
        this.from_customer = from_customer;
        this.time = time;
        this.content_type = content_type;
        this.content = content;
        this.sender_nickname = sender_nickname;
        this.sender_img = sender_img;
    }

    @Override
    public String toString() {
        return "NewChatLogBean [conversation_id=" + conversation_id + ", receiver_id=" + receiver_id + ", sender_id="
                + sender_id + ", from_customer=" + from_customer + ", time=" + time + ", content_type=" + content_type
                + ", content=" + content + ", sender_nickname=" + sender_nickname + ", sender_img=" + sender_img + "]";
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public int getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(int receiver_id) {
        this.receiver_id = receiver_id;
    }

    public int getSender_id() {
        return sender_id;
    }

    public void setSender_id(int sender_id) {
        this.sender_id = sender_id;
    }

    public int getFrom_customer() {
        return from_customer;
    }

    public void setFrom_customer(int from_customer) {
        this.from_customer = from_customer;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getContent_type() {
        return content_type;
    }

    public void setContent_type(int content_type) {
        this.content_type = content_type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender_nickname() {
        return sender_nickname;
    }

    public void setSender_nickname(String sender_nickname) {
        this.sender_nickname = sender_nickname;
    }

    public String getSender_img() {
        return sender_img;
    }

    public void setSender_img(String sender_img) {
        this.sender_img = sender_img;
    }

}
