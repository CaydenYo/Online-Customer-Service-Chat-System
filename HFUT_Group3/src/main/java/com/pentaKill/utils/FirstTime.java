package com.pentaKill.utils;

public class FirstTime {
    String senderId;
    String receiverId;

    public FirstTime(String senderId, String receiverId) {
        super();
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "FirstTime [senderId=" + senderId + ", receiverId=" + receiverId + "]";
    }

    public FirstTime() {
        super();
        // TODO Auto-generated constructor stub
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        FirstTime ft = (FirstTime) obj;
        return ft.senderId.equals(senderId) && ft.receiverId.equals(receiverId);
    }

}
