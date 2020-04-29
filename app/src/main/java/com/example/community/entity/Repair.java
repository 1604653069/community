package com.example.community.entity;

/*报事维修类*/
public class Repair {
    /*需要提交的问题*/
    private String question;
    /*保修上门的时间段*/
    private String date;
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
