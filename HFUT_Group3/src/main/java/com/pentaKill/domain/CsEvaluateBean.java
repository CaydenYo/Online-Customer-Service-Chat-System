package com.pentaKill.domain;

import java.sql.Timestamp;

public class CsEvaluateBean {

    private int cs_id;
    private Timestamp evaluate_time;
    private float cs_score;
    private String content;

    public CsEvaluateBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public CsEvaluateBean(int cs_id, Timestamp evaluate_time, float cs_score, String content) {
        super();
        this.cs_id = cs_id;
        this.evaluate_time = evaluate_time;
        this.cs_score = cs_score;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CsEvaluateBean [cs_id=" + cs_id + ", evaluate_time=" + evaluate_time + ", cs_score=" + cs_score
                + ", content=" + content + "]";
    }

    public int getCs_id() {
        return cs_id;
    }

    public void setCs_id(int cs_id) {
        this.cs_id = cs_id;
    }

    public Timestamp getEvaluate_time() {
        return evaluate_time;
    }

    public void setEvaluate_time(Timestamp evaluate_time) {
        this.evaluate_time = evaluate_time;
    }

    public float getCs_score() {
        return cs_score;
    }

    public void setCs_score(float cs_score) {
        this.cs_score = cs_score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
