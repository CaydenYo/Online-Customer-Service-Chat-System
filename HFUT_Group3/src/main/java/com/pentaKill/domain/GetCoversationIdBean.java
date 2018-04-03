package com.pentaKill.domain;

public class GetCoversationIdBean {

    private int conversation_id;
    private int customer_id;
    private int cs_id;
    public GetCoversationIdBean() {
        super();
        // TODO Auto-generated constructor stub
    }
    public GetCoversationIdBean(int conversation_id, int customer_id, int cs_id) {
        super();
        this.conversation_id = conversation_id;
        this.customer_id = customer_id;
        this.cs_id = cs_id;
    }
    @Override
    public String toString() {
        return "getCoversationIdBean [conversation_id=" + conversation_id + ", customer_id=" + customer_id + ", cs_id="
                + cs_id + "]";
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
    public int getCs_id() {
        return cs_id;
    }
    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }
    
    
}
