package com.infoshareacademy.czerwoni;

public class Answer {

    private int answerId;
    private String answerName;
    private int relatedQuest;

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public int getRelatedQuest() {
        return relatedQuest;
    }

    public void setRelatedQuest(int relatedQuest) {
        this.relatedQuest = relatedQuest;
    }
}