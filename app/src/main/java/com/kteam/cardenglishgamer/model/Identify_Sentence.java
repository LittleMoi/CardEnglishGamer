package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Identify_Sentence {
    private String ID;
    private String q5_audio;
    private String q5_choose1;
    private String q5_choose2;
    private String q5_answer;
    private String q5_text;

    public String getID() {
        return ID;
    }

    public String getQ5_answer() {
        return q5_answer;
    }

    public String getQ5_audio() {
        return q5_audio;
    }

    public String getQ5_choose1() {
        return q5_choose1;
    }

    public String getQ5_choose2() {
        return q5_choose2;
    }

    public String getQ5_text() {
        return q5_text;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setQ5_answer(String q5_answer) {
        this.q5_answer = q5_answer;
    }

    public void setQ5_audio(String q5_audio) {
        this.q5_audio = q5_audio;
    }

    public void setQ5_choose1(String q5_choose1) {
        this.q5_choose1 = q5_choose1;
    }

    public void setQ5_choose2(String q5_choose2) {
        this.q5_choose2 = q5_choose2;
    }

    public void setQ5_text(String q5_text) {
        this.q5_text = q5_text;
    }
}
