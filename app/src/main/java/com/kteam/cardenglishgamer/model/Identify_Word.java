package com.kteam.cardenglishgamer.database;

/**
 * Created by Administrator on 2017/7/12.
 */

public class Identify_Word {
    private String ID;
    private String q3_audio;
    private String q3_choose1;
    private String q3_choose2;
    private String q3_answer;
    private String q3_text;

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getQ3_answer() {
        return q3_answer;
    }

    public String getQ3_audio() {
        return q3_audio;
    }

    public String getQ3_choose1() {
        return q3_choose1;
    }

    public String getQ3_choose2() {
        return q3_choose2;
    }

    public String getQ3_text() {
        return q3_text;
    }

    public void setQ3_answer(String q3_answer) {
        this.q3_answer = q3_answer;
    }

    public void setQ3_audio(String q3_audio) {
        this.q3_audio = q3_audio;
    }

    public void setQ3_choose1(String q3_choose1) {
        this.q3_choose1 = q3_choose1;
    }

    public void setQ3_choose2(String q3_choose2) {
        this.q3_choose2 = q3_choose2;
    }

    public void setQ3_text(String q3_text) {
        this.q3_text = q3_text;
    }

    public String getID() {
        return ID;
    }
}
