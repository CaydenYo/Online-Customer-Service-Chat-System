package com.pentaKill.domain;

public class FastReplyBean {

    private int cs_id;
    private String content;

    public FastReplyBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FastReplyBean(int cs_id, String content) {
        super();
        this.cs_id = cs_id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "FastReplyBean [cs_id=" + cs_id + ", content=" + content + "]";
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
