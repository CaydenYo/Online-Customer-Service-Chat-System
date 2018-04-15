package com.pentaKill.domain;

public class FastReplyBean {
    
    private int shortcut_language_id;
    private int cs_id;
    private String content;

    
    public FastReplyBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public FastReplyBean(int shortcut_language_id, int cs_id, String content) {
        super();
        this.shortcut_language_id = shortcut_language_id;
        this.cs_id = cs_id;
        this.content = content;
    }

    @Override
    public String toString() {
        return "FastReplyBean [shortcut_language_id=" + shortcut_language_id + ", cs_id=" + cs_id + ", content="
                + content + "]";
    }

    public int getShortcut_language_id() {
        return shortcut_language_id;
    }

    public void setShortcut_language_id(int shortcut_language_id) {
        this.shortcut_language_id = shortcut_language_id;
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
