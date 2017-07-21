package com.kteam.cardenglishgamer.model;

/**
 * Created by Bloomkk on 2017/7/12.
 */

public class Cet4_LongDialogue {
    private String ID;
    private String q7_audio;
    private String q7_choose_id;
    private int q7_choose_num;
    private String q7_text;

    public int getQ7_choose_num() {
        return q7_choose_num;
    }

    public String getID() {
        return ID;
    }

    public String getQ7_audio() {
        return q7_audio;
    }

    public String getQ7_choose_id() {
        return q7_choose_id;
    }

    public String getQ7_text() {
        return q7_text;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setQ7_audio(String q7_audio) {
        this.q7_audio = q7_audio;
    }

    public void setQ7_choose_id(String q7_choose_id) {
        this.q7_choose_id = q7_choose_id;
    }

    public void setQ7_choose_num(int q7_choose_num) {
        this.q7_choose_num = q7_choose_num;
    }

    public void setQ7_text(String q7_text) {
        this.q7_text = q7_text;
    }

}
